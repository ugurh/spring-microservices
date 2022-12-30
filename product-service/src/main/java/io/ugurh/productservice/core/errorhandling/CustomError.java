package io.ugurh.productservice.core.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CustomError {

    private LocalDate date;
    private String message;

    public CustomError(String message) {
        this.date = LocalDate.now();
        this.message = message;
    }
}
