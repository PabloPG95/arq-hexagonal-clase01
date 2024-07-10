package com.example.hexagonal.application.ports;

import com.example.hexagonal.domain.model.OrdersCRUD;

import java.util.List;

public interface OrderCrudRepository {

    OrdersCRUD createOrder(OrdersCRUD ordersCRUD);

    void updateOrderName(Long id, String newName);

    void deleteOrder(Long id);

    List<OrdersCRUD> getAllOrders();

    OrdersCRUD getOrderById(Long id);
}
