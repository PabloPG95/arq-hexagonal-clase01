package com.example.hexagonal.application;

import com.example.hexagonal.domain.orderExample.Order;
import com.example.hexagonal.domain.orderExample.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@QuarkusTest
class OrderServiceTest {

    @Inject
    OrderService orderService;

    @Test
    void testCreateOrder() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        Assertions.assertNotNull(order.getId());
    }

    @Test
    void testAddItemToOrder(){
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        BigDecimal price = new BigDecimal("4.5");
        OrderItem item = new OrderItem("Product 1", 1, price);
        orderService.addItemToOrder(order.getId(), item);
        Assertions.assertNotNull(order.getItems());
        Assertions.assertEquals(1, order.getItems().size());
    }

    @Test
    void testUpdateOrderStatus(){
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        orderService.updateOrderStatus(order.getId(), "CONFIRMED");
        Assertions.assertEquals("CONFIRMED", order.getStatus());
    }

    @Test
    void testGetAllOrders(){
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        Assertions.assertEquals(1, orderService.getAllOrders().size());
    }

    @Test
    void testFindOrderById(){
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        Order foundOrder = orderService.findOrderById(order.getId());
        Assertions.assertNotNull(foundOrder);
    }
}
