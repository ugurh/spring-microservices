package io.ugurh.orderservice.command.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
    private String orderId;
    private String userId;
    private String productId;
    private String addressId;
    private OrderStatus orderStatus;
    private Integer quantity;
}
