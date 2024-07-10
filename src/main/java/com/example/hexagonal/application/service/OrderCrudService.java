package com.example.hexagonal.application.service;

import com.example.hexagonal.application.ports.OrderCrudRepository;
import com.example.hexagonal.domain.model.OrdersCRUD;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class OrderCrudService {

    @Inject
    OrderCrudRepository orderCrudRepository;

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
}
