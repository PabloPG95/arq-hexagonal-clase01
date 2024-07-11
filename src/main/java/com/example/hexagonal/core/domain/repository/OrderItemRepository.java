package com.example.hexagonal.core.domain.repository;

import com.example.hexagonal.core.domain.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository {
    OrderItem getOrderItemById(int orderItemId);

    OrderItem createOrderItem(OrderItem orderItem);

    void updateOrderItem(int orderItemId, OrderItem orderItem);

    void deleteOrderItem(int orderItemId);

    List<OrderItem> getAllOrderItems();
}
