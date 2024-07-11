package com.example.hexagonal.core.domain.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class OrdersCRUD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderName;

    private String status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItemList;

    public OrdersCRUD(){
    }

    public OrdersCRUD(String orderName, String status, List<OrderItem> orderItemList){
        this.orderName = orderName;
        this.status = status;
        this.orderItemList = orderItemList;
    }

    public Long getId() {
        return id;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItemList.add(orderItem);
    }

    public void removeOrderItem(OrderItem o) {
        orderItemList.remove(o);
    }


}
