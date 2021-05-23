package com.ecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "order_tb")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer customer;

    private String date;
    private boolean delivered;
    private boolean canceled;


    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Product> products;


}
