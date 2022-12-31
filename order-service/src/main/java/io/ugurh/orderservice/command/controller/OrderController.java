package io.ugurh.orderservice.command.controller;

import io.ugurh.orderservice.command.model.CreateOrderCommand;
import io.ugurh.orderservice.command.model.OrderCreateDto;
import io.ugurh.orderservice.command.model.OrderStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CommandGateway commandGateway;

    public OrderController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        CreateOrderCommand orderCommand = CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .orderStatus(OrderStatus.CREATED)
                .productId(orderCreateDto.getProductId())
                .quantity(orderCreateDto.getQuantity())
                .addressId(orderCreateDto.getAddressId())
                .userId(UUID.randomUUID().toString())
                .build();

        String response = commandGateway.sendAndWait(orderCommand);

        return ResponseEntity.ok(response);
    }
}
