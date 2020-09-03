package com.moki.lostandfound.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToMany
    public List<Item> items;
}
