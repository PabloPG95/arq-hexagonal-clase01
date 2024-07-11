package com.example.hexagonal.adapter.secondary.persistence;

import com.example.hexagonal.core.domain.entity.OrderItem;
import com.example.hexagonal.core.domain.repository.OrderItemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class OrderItemRepositoryImpl implements OrderItemRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public OrderItem getOrderItemById(int orderItemId) {
        return em.find(OrderItem.class, orderItemId);
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        em.persist(orderItem);
        return orderItem;
    }

    @Override
    public void updateOrderItem(int orderItemId, OrderItem orderItem) {
        OrderItem orderItemToUpdate = em.find(OrderItem.class, orderItemId);
        if(orderItemToUpdate != null){
            orderItemToUpdate.setProductName(orderItem.getProductName());
            orderItemToUpdate.setQuantity(orderItem.getQuantity());
            orderItemToUpdate.setPrice(orderItem.getPrice());
            em.merge(orderItemToUpdate);
        } else {
            throw new IllegalArgumentException("OrderItem not found");
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        OrderItem orderItem = em.find(OrderItem.class, orderItemId);
        if(orderItem != null){
            em.remove(orderItem);
        } else {
            throw new IllegalArgumentException("OrderItem not found");
        }
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        TypedQuery<OrderItem> query = em.createQuery("SELECT o FROM OrderItem o", OrderItem.class);
        return query.getResultList();
    }
}
