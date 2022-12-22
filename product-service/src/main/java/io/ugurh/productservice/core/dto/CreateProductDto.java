package io.ugurh.productservice.core.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class CreateProductDto {

    @NotBlank(message = "Title cannot be empty")
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
