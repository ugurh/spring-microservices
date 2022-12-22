package io.ugurh.productservice.query;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FindProductsQuery {
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;

}
