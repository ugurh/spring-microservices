package io.ugurh.orderservice.command.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {

    private String productId;
    private Integer quantity;
    private String addressId;
}
