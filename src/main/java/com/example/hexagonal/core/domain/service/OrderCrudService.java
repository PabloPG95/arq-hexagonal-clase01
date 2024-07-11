package com.example.hexagonal.core.domain.service;

import com.example.hexagonal.core.domain.entity.OrderItem;
import com.example.hexagonal.core.domain.repository.OrderCrudRepository;
import com.example.hexagonal.core.domain.entity.OrdersCRUD;
import com.example.hexagonal.core.domain.repository.OrderItemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class OrderCrudService {

    @Inject
    OrderCrudRepository orderCrudRepository;

    @Inject
    OrderItemRepository orderItemRepository;

    public OrdersCRUD createOrder(OrdersCRUD o) {
        return orderCrudRepository.createOrder(o);
    }

    public void updateOrderName(Long id, String newName) {
        orderCrudRepository.updateOrderName(id, newName);
    }

    public void deleteOrder(Long id) {
        orderCrudRepository.deleteOrder(id);
    }

    public OrdersCRUD getOrderById(Long id) {
        return orderCrudRepository.getOrderById(id);
    }

    public List<OrdersCRUD> getAllOrders() {
        return orderCrudRepository.getAllOrders();
    }

    public void updateOrderStatus(Long orderId, String status){
        OrdersCRUD orders = orderCrudRepository.getOrderById(orderId);
        if(orders != null) {
            orders.setStatus(status);
            orderCrudRepository.updateOrderStatus(orderId, status);
        }
    }

    public void addItemToOrder(Long orderId, OrderItem item){
        OrdersCRUD orders = orderCrudRepository.getOrderById(orderId);
        if(orders != null) {
            orders.addOrderItem(item);
        }
    }

    public void removeItemFromOrder(Long orderId, int orderItemId){
        OrdersCRUD orders = orderCrudRepository.getOrderById(orderId);
        OrderItem orderItem = orderItemRepository.getOrderItemById(orderItemId);
        if(orders != null && orderItem != null) {
            orders.removeOrderItem(orderItem);
        }
    }


}
