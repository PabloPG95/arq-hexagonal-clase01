package com.example.hexagonal.core.domain.repository;

import com.example.hexagonal.core.domain.entity.OrderItem;
import com.example.hexagonal.core.domain.entity.OrdersCRUD;

import java.util.List;

public interface OrderCrudRepository {

    OrdersCRUD createOrder(OrdersCRUD ordersCRUD);

    void updateOrderName(Long id, String newName);

    void updateOrderStatus(Long orderId, String status);

    void deleteOrder(Long id);

    List<OrdersCRUD> getAllOrders();

    OrdersCRUD getOrderById(Long id);

    void addItemToOrder(Long orderId, OrderItem item);

    void removeItemFromOrder(Long orderId, int orderItemId);
}
