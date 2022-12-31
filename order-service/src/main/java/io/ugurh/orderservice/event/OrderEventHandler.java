package io.ugurh.orderservice.event;

import io.ugurh.orderservice.command.model.OrderCreatedEvent;
import io.ugurh.orderservice.command.model.OrderEntity;
import io.ugurh.orderservice.command.repository.OrderEntityRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderEventHandler.class);

    private final OrderEntityRepository orderEntityRepository;

    public OrderEventHandler(OrderEntityRepository orderEntityRepository) {
        this.orderEntityRepository = orderEntityRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        LOGGER.info(String.format("OrderCreatedEvent is called. OrderId %s and productID %s ",event.getOrderId(), event.getProductId()));
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(event, orderEntity);
        orderEntityRepository.save(orderEntity);
    }
}
