package com.example.hexagonal.adapter.in;

import com.example.hexagonal.application.OrderService;
import com.example.hexagonal.domain.orderExample.Order;
import com.example.hexagonal.domain.orderExample.OrderItem;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderService orderService;

    @POST
    public Response createOrder(Order order){
        orderService.createOrder(order);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @POST
    @Path("/{orderId}/items")
    public Response addItemToOrder(@PathParam("orderId") Long orderId, OrderItem item){
        orderService.addItemToOrder(orderId, item);
        return Response.ok().build();
    }

    @PUT
    @Path("/{orderId}/status")
    public Response updateOrderStatus(@PathParam("orderId") Long orderId, String status){
        orderService.updateOrderStatus(orderId, status);
        return Response.ok().build();
    }

    @GET
    public Response getAllOrders(){
        return Response.ok(orderService.getAllOrders()).build();
    }

    @GET
    @Path("/{orderId}")
    public Response findOrderById(@PathParam("orderId") Long orderId){
        Order order = orderService.findOrderById(orderId);
        if(order != null){
            return Response.ok(order).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
