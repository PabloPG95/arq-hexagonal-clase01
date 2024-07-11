package com.example.hexagonal.core.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdersCRUD> orders;

    public Customer() {
    }

    public Customer(String name, Address address, List<OrdersCRUD> orders) {
        this.name = name;
        this.address = address;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrdersCRUD> getOrders() {
        return orders;
    }

    public void addOrder(OrdersCRUD order) {
        orders.add(order);
    }

    public void removeOrder(int id) {
        orders.remove(id);
    }

}
