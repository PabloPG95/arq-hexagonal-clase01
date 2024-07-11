package com.example.hexagonal.adapter.secondary.persistence;

import com.example.hexagonal.core.domain.entity.OrderItem;
import com.example.hexagonal.core.domain.repository.OrderCrudRepository;
import com.example.hexagonal.core.domain.entity.OrdersCRUD;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class OrderRepositoryImpl implements OrderCrudRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public OrdersCRUD createOrder(OrdersCRUD ordersCRUD) {
        em.persist(ordersCRUD);
        return ordersCRUD;
    }

    @Override
    public void updateOrderName(Long id, String newName) {
        OrdersCRUD o = em.find(OrdersCRUD.class, id);
        if(o != null){
            o.setOrderName(newName);
            em.merge(o);
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }

    @Override
    public void deleteOrder(Long id) {
        OrdersCRUD o = em.find(OrdersCRUD.class, id);
        if(o != null){
            em.remove(o);
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }

    @Override
    public List<OrdersCRUD> getAllOrders() {
        TypedQuery<OrdersCRUD> query = em.createQuery("SELECT o FROM OrdersCRUD o", OrdersCRUD.class);
        return query.getResultList();
    }

    @Override
    public OrdersCRUD getOrderById(Long id) {
        return em.find(OrdersCRUD.class, id);
    }

    @Override
    public void updateOrderStatus(Long orderId, String status) {
        OrdersCRUD o = em.find(OrdersCRUD.class, orderId);
        if(o != null){
            o.setStatus(status);
            em.merge(o);
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }

    @Override
    public void addItemToOrder(Long orderId, OrderItem item) {
        OrdersCRUD o = em.find(OrdersCRUD.class, orderId);
        if(o != null){
            o.addOrderItem(item);
            em.merge(o);
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }

    @Override
    public void removeItemFromOrder(Long orderId, int orderItemId) {
        OrdersCRUD o = em.find(OrdersCRUD.class, orderId);
        if(o != null){
            OrderItem orderItem = em.find(OrderItem.class, orderItemId);
            if(orderItem != null){
                o.removeOrderItem(orderItem);
                em.merge(o);
            } else {
                throw new IllegalArgumentException("Order item not found");
            }
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }
}
