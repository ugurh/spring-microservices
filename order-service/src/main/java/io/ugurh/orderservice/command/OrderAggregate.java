package io.ugurh.orderservice.command;

import io.ugurh.orderservice.command.model.CreateOrderCommand;
import io.ugurh.orderservice.command.model.OrderCreatedEvent;
import io.ugurh.orderservice.command.model.OrderStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private String orderId;
    private String userId;
    private String productId;
    private String addressId;
    private OrderStatus orderStatus;
    private Integer quantity;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) throws Exception {
        validate(createOrderCommand);

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();

        BeanUtils.copyProperties(createOrderCommand, orderCreatedEvent);

        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        this.addressId = orderCreatedEvent.getAddressId();
        this.orderId = orderCreatedEvent.getOrderId();
        this.quantity = orderCreatedEvent.getQuantity();
        this.productId = orderCreatedEvent.getProductId();
        this.orderStatus = orderCreatedEvent.getOrderStatus();
        this.userId = orderCreatedEvent.getUserId();
    }

    private void validate(CreateOrderCommand createOrderCommand) throws Exception {
        if (!OrderStatus.CREATED.toString().equals(createOrderCommand.getOrderStatus().toString())) {
            throw new Exception("Wrong order status is sent");
        }
    }
}
