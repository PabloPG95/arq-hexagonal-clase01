package com.example.hexagonal.adapter.secondary;

import com.example.hexagonal.domain.model.Orders;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Orders> {
}
