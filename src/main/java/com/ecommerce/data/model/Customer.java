package com.ecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    @JsonIgnore
    private Set<Card> cards;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @JsonIgnore
    private Set<Order> orders;

    public void setAddresses (Address address) {
        if (addresses == null) {
            addresses = new ArrayList<>();
        }
        addresses.add(address);
    }

    public void setCards (Card card) {
        if (cards == null) {
            cards = new HashSet<>();
        }
        cards.add(card);
    }

}
