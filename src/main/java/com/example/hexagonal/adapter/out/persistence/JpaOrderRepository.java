package com.example.hexagonal.adapter.out.persistence;

import com.example.hexagonal.application.ports.OrderCrudRepository;
import com.example.hexagonal.domain.model.OrdersCRUD;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class JpaOrderRepository implements OrderCrudRepository {

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
}
