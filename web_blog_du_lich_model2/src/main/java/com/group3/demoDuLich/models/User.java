package com.group3.demoDuLich.models;

import javax.persistence.*;
import lombok.*;
import org.springframework.data.repository.query.Param;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String img;
    private boolean gender;
    @ManyToOne
    private Role role;
}
