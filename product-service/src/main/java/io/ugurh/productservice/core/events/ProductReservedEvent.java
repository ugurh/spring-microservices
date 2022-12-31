package io.ugurh.productservice.core.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductReservedEvent {

    private String orderId;
    private String productId;
    private String userId;
    private Integer quantity;
}
