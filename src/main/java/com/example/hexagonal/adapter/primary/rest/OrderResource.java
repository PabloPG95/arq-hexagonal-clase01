package com.example.hexagonal.adapter.primary.rest;

import com.example.hexagonal.application.OrderService;
import com.example.hexagonal.domain.model.Orders;
import com.example.hexagonal.core.domain.entity.OrderItem;
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
    public Response createOrder(Orders orders){
        orderService.createOrder(orders);
        return Response.status(Response.Status.CREATED).entity(orders).build();
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
        Orders orders = orderService.findOrderById(orderId);
        if(orders != null){
            return Response.ok(orders).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
