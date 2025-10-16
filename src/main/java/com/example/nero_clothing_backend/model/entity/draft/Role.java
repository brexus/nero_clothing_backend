package com.example.nero_clothing_backend.model.entity.draft;

import com.example.nero_clothing_backend.model.entity.BaseModel;
import com.example.nero_clothing_backend.model.entity.User;
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

    @OneToMany(mappedBy = "role")
    private java.util.List<User> users;

}
