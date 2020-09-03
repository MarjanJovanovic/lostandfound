package com.moki.lostandfound.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;

    @ManyToMany
    private Set<Role> roles;

    @OneToMany
    private List<Item> items;
}
