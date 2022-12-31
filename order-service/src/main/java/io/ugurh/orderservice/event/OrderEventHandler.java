package io.ugurh.orderservice.event;

import io.ugurh.orderservice.command.model.OrderCreatedEvent;
import io.ugurh.orderservice.command.model.OrderEntity;
import io.ugurh.orderservice.command.repository.OrderEntityRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

    private final OrderEntityRepository orderEntityRepository;

    public OrderEventHandler(OrderEntityRepository orderEntityRepository) {
        this.orderEntityRepository = orderEntityRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(event, orderEntity);
        orderEntityRepository.save(orderEntity);
    }
}
