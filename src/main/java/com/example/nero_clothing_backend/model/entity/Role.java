package com.example.nero_clothing_backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseModel {

    private String name;

    //imo tutaj bym wyjebał to users, nie ma sensu trzymać listy userow w roli
    @OneToMany(mappedBy = "role")
    private java.util.List<User> users;
}
