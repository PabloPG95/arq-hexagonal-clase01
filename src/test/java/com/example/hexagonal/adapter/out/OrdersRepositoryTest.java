package com.example.hexagonal.adapter.out;

import com.example.hexagonal.domain.orderexample.Orders;
import com.example.hexagonal.domain.orderexample.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class OrdersRepositoryTest {


    @Test
    void testCreateOrderEndpoint(){
        Orders orders = new Orders(LocalDateTime.now(), "PENDING");
        given()
                .contentType("application/json")
                .body(orders)
                .when().post("/orders")
                .then()
                .statusCode(201)
                .body("status", is("PENDING"));
    }

    @Test
    void testAddItemToOrderEndpoint(){
        Orders orders = new Orders(LocalDateTime.now(), "PENDING");
        given()
                .contentType("application/json")
                .body(orders)
                .when().post("/orders")
                .then()
                .statusCode(201);

        BigDecimal price = new BigDecimal("4.5");
        OrderItem item = new OrderItem("Product 1", 1, price);
        given()
                .contentType("application/json")
                .body(item)
                .when().post("/orders/" + orders.getId() + "/items")
                .then()
                .statusCode(200);
    }

    @Test
    void testUpdateOrderStatusEndpoint(){
        Orders orders = new Orders(LocalDateTime.now(), "PENDING");
        given()
                .contentType("application/json")
                .body(orders)
                .when().post("/orders")
                .then()
                .statusCode(201);

        given()
                .contentType("application/json")
                .body("CONFIRMED")
                .when().put("/orders/" + orders.getId() + "/status")
                .then()
                .statusCode(200);
    }
}