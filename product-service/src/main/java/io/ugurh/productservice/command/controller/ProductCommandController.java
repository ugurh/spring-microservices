package io.ugurh.productservice.command.controller;

import io.ugurh.productservice.core.command.CreateProductCommand;
import io.ugurh.productservice.core.dto.CreateProductDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private final CommandGateway commandGateway;
    private final Environment env;

    @Autowired
    public ProductCommandController(CommandGateway commandGateway, Environment env) {
        this.commandGateway = commandGateway;
        this.env = env;
    }


//    @GetMapping
//    public ResponseEntity<String> get() {
//        return ResponseEntity.ok("Product service is running on " + env.getProperty("local.server.port"));
//    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateProductDto createProductDto) {
        CreateProductCommand productCommand = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .title(createProductDto.getTitle())
                .price(createProductDto.getPrice())
                .quantity(createProductDto.getQuantity())
                .build();
        String response = null;
        try {
          response = commandGateway.sendAndWait(productCommand);
        }catch (Exception ex){
            response = ex.getLocalizedMessage();
        }
        return ResponseEntity.ok(response);
    }
}
