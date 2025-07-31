package com.example.nero_clothing_backend.repository;

import com.example.nero_clothing_backend.model.entity.Address;
import com.example.nero_clothing_backend.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
/* tutaj przekmin sobie kiedys to n+1 zapytan, jakbys potrzebowal pobrac
np 20 zamowien i miec od razu itemy zamowione to zrobienie
`
List<Order> o =orderRepository.findAll() i potem
for(Order order : o) {
    order.getItems();  to spowoduje 20 kolejnych zapytan, bo dla kazdego zamowienia bedzie osobne zapytanie do bazy
 }
`
wiec jakbys chcial pobrac zamowienia z itemami to
List<Order> o = orderRepository.findAllWithItems(); // to by musialo byc w repozytorium

i wtedy w repo:
 @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items") -- wykona sie jedno zapytanie
findAllWithItems()


 */
}
