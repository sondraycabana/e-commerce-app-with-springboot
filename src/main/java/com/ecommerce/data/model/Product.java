package com.ecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String expDate;

    private Double price;

    private Integer quantity;
    @JsonIgnore
    @ManyToMany(mappedBy = "products",fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private List<Order> orders;

}
