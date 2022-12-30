package io.ugurh.orderservice.command.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@Builder
public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;
    private final String userId;
    private final String productId;
    private final String addressId;
    private final OrderStatus orderStatus;
    private Integer quantity;

}
