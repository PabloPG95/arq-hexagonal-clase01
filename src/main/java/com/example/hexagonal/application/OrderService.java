package com.example.hexagonal.application;


import com.example.hexagonal.adapter.secondary.OrderRepository;
import com.example.hexagonal.domain.model.Orders;
import com.example.hexagonal.core.domain.entity.OrderItem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    @Transactional
    public Orders createOrder(Orders orders){
        orderRepository.persist(orders);
        return orders;
    }

    @Transactional
    public void addItemToOrder(Long orderId, OrderItem item){
        Orders orders = orderRepository.findById(orderId);
        if(orders != null) {
            orders.addItem(item);
            orderRepository.persist(orders);
        }
    }

    @Transactional
    public void updateOrderStatus(Long orderId, String status){
        Orders orders = orderRepository.findById(orderId);
        if(orders != null) {
            orders.updateStatus(status);
            orderRepository.persist(orders);
        }
    }

    public List<Orders> getAllOrders(){
        return orderRepository.listAll();
    }

    public Orders findOrderById(Long orderId){
        return orderRepository.findById(orderId);
    }
}
