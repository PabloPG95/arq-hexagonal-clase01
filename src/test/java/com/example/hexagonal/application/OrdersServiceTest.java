package com.example.hexagonal.application;

import com.example.hexagonal.domain.orderexample.Orders;
import com.example.hexagonal.domain.orderexample.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@QuarkusTest
class OrdersServiceTest {

    @Inject
    OrderService orderService;

    @Test
    void testCreateOrder() {
        Orders orders = new Orders(LocalDateTime.now(), "PENDING");
        orderService.createOrder(orders);
        Assertions.assertNotNull(orders.getId());
    }

    @Test
    void testAddItemToOrder(){
        Orders orders = new Orders(LocalDateTime.now(), "PENDING");
        orderService.createOrder(orders);
        BigDecimal price = new BigDecimal("4.5");
        OrderItem item = new OrderItem("Product 1", 1, price);
        orderService.addItemToOrder(orders.getId(), item);
        Assertions.assertNotNull(orders.getItems());
        Assertions.assertEquals(1, orders.getItems().size());
    }

    @Test
    void testUpdateOrderStatus(){
        Orders orders = new Orders(LocalDateTime.now(), "PENDING");
        orderService.createOrder(orders);
        orderService.updateOrderStatus(orders.getId(), "CONFIRMED");
        Assertions.assertEquals("CONFIRMED", orders.getStatus());
    }

    @Test
    void testGetAllOrders(){
        Orders orders = new Orders(LocalDateTime.now(), "PENDING");
        orderService.createOrder(orders);
        Assertions.assertEquals(1, orderService.getAllOrders().size());
    }

    @Test
    void testFindOrderById(){
        Orders orders = new Orders(LocalDateTime.now(), "PENDING");
        orderService.createOrder(orders);
        Orders foundOrders = orderService.findOrderById(orders.getId());
        Assertions.assertNotNull(foundOrders);
    }
}
