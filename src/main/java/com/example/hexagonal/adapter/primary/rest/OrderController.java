package com.example.hexagonal.adapter.primary.rest;

import com.example.hexagonal.core.domain.entity.OrderItem;
import com.example.hexagonal.core.domain.service.OrderCrudService;
import com.example.hexagonal.core.domain.entity.OrdersCRUD;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/order-crud")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController {
    @Inject
    OrderCrudService orderCrudService;

    @GET
    public Response getAllOrders(){
        List<OrdersCRUD> ordersCRUDList = orderCrudService.getAllOrders();
        return Response.ok(ordersCRUDList).build();
    }

    @GET
    @Path("/{orderId}")
    public Response getOrderById(@PathParam("orderId") Long orderId){
        OrdersCRUD foundOrder = orderCrudService.getOrderById(orderId);
        return Response.ok(foundOrder).build();
    }

    @POST
    public Response createOrder(OrdersCRUD orders){
        OrdersCRUD createdOrder = orderCrudService.createOrder(orders);
        return Response.status(Response.Status.CREATED).entity(createdOrder).build();
    }

    @PUT
    @Path("/{orderId}")
    public Response updateOrderName(@PathParam("orderId") Long orderId, String newName){
        orderCrudService.updateOrderName(orderId, newName);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{orderId}")
    public Response deleteOrder(@PathParam("orderId") Long orderId){
        orderCrudService.deleteOrder(orderId);
        return Response.ok().build();
    }


    @PUT
    @Path("/{orderId}/status")
    public Response updateOrderStatus(@PathParam("orderId") Long orderId, String status){
        orderCrudService.updateOrderStatus(orderId, status);
        return Response.ok().build();
    }

    @POST
    @Path("/{orderId}/items")
    public Response addItemToOrder(@PathParam("orderId") Long orderId, OrderItem item){
        orderCrudService.addItemToOrder(orderId, item);
        return Response.ok().build();
    }

    @PUT
    @Path("/{orderId}/items/{orderItemId}")
    public Response removeItemFromOrder(@PathParam("orderId") Long orderId, @PathParam("orderItemId") int orderItemId) {
        orderCrudService.removeItemFromOrder(orderId, orderItemId);
        return Response.ok().build();
    }

}
