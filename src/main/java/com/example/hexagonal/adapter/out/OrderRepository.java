package com.example.hexagonal.adapter.out;

import com.example.hexagonal.domain.orderexample.Orders;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Orders> {
}
