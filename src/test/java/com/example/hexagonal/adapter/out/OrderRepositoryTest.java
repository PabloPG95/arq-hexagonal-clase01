package com.example.hexagonal.adapter.out;

import com.example.hexagonal.domain.orderExample.Order;
import com.example.hexagonal.domain.orderExample.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class OrderRepositoryTest {


    @Test
    void testCreateOrderEndpoint(){
        Order order = new Order(LocalDateTime.now(), "PENDING");
        given()
                .contentType("application/json")
                .body(order)
                .when().post("/orders")
                .then()
                .statusCode(201)
                .body("status", is("PENDING"));
    }

    @Test
    void testAddItemToOrderEndpoint(){
        Order order = new Order(LocalDateTime.now(), "PENDING");
        given()
                .contentType("application/json")
                .body(order)
                .when().post("/orders")
                .then()
                .statusCode(201);

        BigDecimal price = new BigDecimal("4.5");
        OrderItem item = new OrderItem("Product 1", 1, price);
        given()
                .contentType("application/json")
                .body(item)
                .when().post("/orders/" + order.getId() + "/items")
                .then()
                .statusCode(200);
    }

    @Test
    void testUpdateOrderStatusEndpoint(){
        Order order = new Order(LocalDateTime.now(), "PENDING");
        given()
                .contentType("application/json")
                .body(order)
                .when().post("/orders")
                .then()
                .statusCode(201);

        given()
                .contentType("application/json")
                .body("CONFIRMED")
                .when().put("/orders/" + order.getId() + "/status")
                .then()
                .statusCode(200);
    }
}