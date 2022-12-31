package io.ugurh.orderservice.command;

import io.ugurh.core.commands.ReserveProductCommand;
import io.ugurh.orderservice.command.model.OrderCreatedEvent;
import io.ugurh.productservice.core.events.ProductReservedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Saga
public class OrderSaga {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderSaga.class);
    private final transient CommandGateway commandGateway;

    public OrderSaga(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent orderCreatedEvent) {

        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .orderId(orderCreatedEvent.getOrderId())
                .productId(orderCreatedEvent.getProductId())
                .userId(orderCreatedEvent.getUserId())
                .quantity(orderCreatedEvent.getQuantity())
                .build();

        LOGGER.info(String.format("OrderCreatedEvent handled for orderId %s and productId %s", reserveProductCommand.getOrderId(), reserveProductCommand.getProductId()));

        commandGateway.send(reserveProductCommand, (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                // TODO
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservedEvent productReservedEvent) {
        LOGGER.info(String.format("ProductReservedEvent handled for orderId %s and productId %s", productReservedEvent.getOrderId(), productReservedEvent.getProductId()));
    }
}
