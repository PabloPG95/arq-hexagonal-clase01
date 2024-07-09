package com.example.hexagonal.domain.orderExample;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime creationDate;


    private String status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {
    }

    public Order(LocalDateTime creationDate, String status) {
        this.creationDate = creationDate;
        this.status = status;
    }

    public void addItem(OrderItem item){
        items.add(item);
    }

    public void updateStatus(String status){
        this.status = status;
    }

    public Long getId() {
        return id;
    }


    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public List<OrderItem> getItems(){
        return items;
    }




}
