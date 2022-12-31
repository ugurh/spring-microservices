package io.ugurh.orderservice.command.model;

import lombok.Data;

@Data
public class OrderCreatedEvent {
    private String orderId;
    private String userId;
    private String productId;
    private String addressId;
    private OrderStatus orderStatus;
    private Integer quantity;
}
