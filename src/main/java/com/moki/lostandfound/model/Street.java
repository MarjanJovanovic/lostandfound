package com.moki.lostandfound.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    private List<Item> items;

//    medju tabela?
    @ManyToMany
    private List<City> cities;
}
