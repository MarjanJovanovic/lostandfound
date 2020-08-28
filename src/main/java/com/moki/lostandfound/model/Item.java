package com.moki.lostandfound.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean isLost;

    @OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST)
    private List<Image> images;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Street street;
}
