package com.example.hexagonal.application;

import com.example.hexagonal.core.domain.entity.OrdersCRUD;
import com.example.hexagonal.core.domain.service.OrderCrudService;
import com.example.hexagonal.domain.model.Orders;
import com.example.hexagonal.core.domain.entity.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@QuarkusTest
class OrdersServiceTest {

    @Inject
    OrderCrudService orderService;

    @Test
    void testCreateOrder() {
        OrderItem oItem = new OrderItem("Product 1", 1, new BigDecimal("4.5"));
        OrdersCRUD orders = new OrdersCRUD("test", "PENDING", List.of(oItem));
        orderService.createOrder(orders);
        Assertions.assertNotNull(orders.getId());
        Assertions.assertEquals("test", orders.getOrderItemList().get(0).getProductName());
    }

    @Test
    void testAddItemToOrder(){
        OrderItem oItem = new OrderItem("Product 1", 1, new BigDecimal("4.5"));
        OrdersCRUD orders = new OrdersCRUD("test", "PENDING", List.of(oItem));
        orderService.createOrder(orders);
        BigDecimal price = new BigDecimal("4.5");
        OrderItem item = new OrderItem("Product 2", 1, price);
        orderService.addItemToOrder(orders.getId(), item);
        Assertions.assertNotNull(orders.getOrderItemList());
        Assertions.assertEquals(2, orders.getOrderItemList().size());
    }

    @Test
    void testUpdateOrderStatus(){
        OrderItem oItem = new OrderItem("Product 1", 1, new BigDecimal("4.5"));
        OrdersCRUD orders = new OrdersCRUD("test", "PENDING", List.of(oItem));
        orderService.updateOrderStatus(orders.getId(), "CONFIRMED");
        Assertions.assertEquals("CONFIRMED", orders.getStatus());
    }

    @Test
    void testGetAllOrders(){
        OrderItem oItem = new OrderItem("Product 1", 1, new BigDecimal("4.5"));
        OrdersCRUD orders = new OrdersCRUD("test", "PENDING", List.of(oItem));
        orderService.createOrder(orders);
        Assertions.assertEquals(1, orderService.getAllOrders().size());
    }

    @Test
    void testFindOrderById(){
        OrderItem oItem = new OrderItem("Product 1", 1, new BigDecimal("4.5"));
        OrdersCRUD orders = new OrdersCRUD("test", "PENDING", List.of(oItem));
        orderService.createOrder(orders);
        OrdersCRUD foundOrders = orderService.getOrderById(orders.getId());
        Assertions.assertNotNull(foundOrders);
    }
}
