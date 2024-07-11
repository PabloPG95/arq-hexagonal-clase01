package com.example.hexagonal.domain.model;


import com.example.hexagonal.core.domain.entity.Customer;
import com.example.hexagonal.core.domain.entity.OrderItem;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime creationDate;


    private String status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    private Customer c;

    public Orders() {
    }

    public Orders(LocalDateTime creationDate, String status) {
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
