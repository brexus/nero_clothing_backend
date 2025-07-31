package com.example.nero_clothing_backend.service.Order;

import com.example.nero_clothing_backend.exception.*;
import com.example.nero_clothing_backend.mapper.OrderItemMapper;
import com.example.nero_clothing_backend.mapper.OrderMapper;
import com.example.nero_clothing_backend.model.dto.Order.OrderRequestDto;
import com.example.nero_clothing_backend.model.dto.Order.OrderResponseDto;
import com.example.nero_clothing_backend.model.dto.OrderItem.OrderItemRequestDto;
import com.example.nero_clothing_backend.model.dto.OrderItem.OrderItemResponseDto;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantRequestDto;
import com.example.nero_clothing_backend.model.entity.*;
import com.example.nero_clothing_backend.model.enums.OrderStatus;
import com.example.nero_clothing_backend.repository.OrderRepository;
import com.example.nero_clothing_backend.repository.ProductRepository;
import com.example.nero_clothing_backend.repository.ProductVariantRepository;
import com.example.nero_clothing_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    public final OrderRepository orderRepository;
    public final UserRepository userRepository;
    public final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;


    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto reqDto) {
        if(reqDto == null) { // chyba jak @Valid w kontrolerze to nie bedzie null nigdy
            return null;
        }

//        (1). Request -> Entity
        Order order = OrderMapper.toEntity(reqDto);

//        (2). (List<OrderItemRequestDto> --> List<OrderItem>) and check stock availability

        List<OrderItem> orderItemList = new ArrayList<>();

        for(OrderItemRequestDto item : reqDto.getItems()) {
            // (A). Get ProductVariant
            ProductVariant productVariant = productVariantRepository.findById(item.getProductVariantId())
                    .orElseThrow(() -> new ProductVariantNotFound(item.getProductVariantId()));


            // (B). Check stock availability
            // i tutaj bardzo wazne jest uzycie transcational, bo majac np 1000 userów
            // kilku w tym samym czasie moze zlozyc zamowienie na ten sam produkt i wtedy chujnia jak sie to wykona rownolegle
            if(productVariant.getStockQuantity() < item.getQuantity()) {
                throw new InsufficientStockException(item.getProductVariantId(), productVariant.getStockQuantity(), item.getQuantity());
            }

            // (C). Subtract from stock
            productVariant.setStockQuantity(productVariant.getStockQuantity() - item.getQuantity());
            productVariantRepository.save(productVariant);

            // (D). OrderItem Request DTO -> Entity
            Double unitPrice = productVariant.getProduct().getPrice();
// ogolnie to zjebane ale Double i Float są chujowe do cen i ogolnie są chujowe
          //  https://dzone.com/articles/never-use-float-and-double-for-monetary-calculatio
            // najlepsza opcja imo to przechowywanie w Long jako groszach i tylko przy wyswietlaniu na froncie dzielenie na 100
            // albo zwracac w DTO jako String z dwoma miejscami po przecinku juz gotowe

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .productVariant(productVariant)
                    .unitPrice(unitPrice)
                    .quantity(item.getQuantity())
                    .totalPrice(unitPrice*item.getQuantity())
                    .build();

            orderItemList.add(orderItem);
        }

        order.setItems(orderItemList);

//      (3). Set USER
        User user = userRepository.findById(reqDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(reqDto.getUserId()));

        order.setUser(user);

//      (4). status -> "NEW"
        order.setStatus(OrderStatus.NEW);

//      (5). calculate total amount
        Double totalAmount = orderItemList.stream().mapToDouble(OrderItem::getTotalPrice).sum();
        order.setTotalAmount(totalAmount);

//      (6). save to DB
        Order saved = orderRepository.save(order);

        return OrderMapper.toDto(saved);
    }

    @Override
    public OrderResponseDto updatePartialOrder(OrderRequestDto reqDto, Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));


        return null;
    }

    @Override
    public Boolean hasSufficientStock(ProductVariant productVariant, Integer requestQuantity) {
        return productVariant.getStockQuantity() >= requestQuantity;
    }
}
