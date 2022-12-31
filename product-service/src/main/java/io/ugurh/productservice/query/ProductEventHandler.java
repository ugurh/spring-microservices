package io.ugurh.productservice.query;

import io.ugurh.productservice.core.data.entity.ProductEntity;
import io.ugurh.productservice.core.data.repository.ProductEntityRepository;
import io.ugurh.productservice.core.events.ProductCreatedEvent;
import io.ugurh.productservice.core.events.ProductReservedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("product-group")
public class ProductEventHandler {
    private ProductEntityRepository productEntityRepository;

    public ProductEventHandler(ProductEntityRepository productEntityRepository) {
        this.productEntityRepository = productEntityRepository;
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handleException(Exception exception) throws Exception {
        throw exception;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        productEntityRepository.save(productEntity);
    }

    @EventHandler
    public void on(ProductReservedEvent productReservedEvent) {
        Optional<ProductEntity> product = productEntityRepository.findById(productReservedEvent.getProductId());

        if (product.isEmpty()) {
            throw new IllegalArgumentException(String.format("Product %s is not found ", productReservedEvent.getProductId()));
        }

        ProductEntity productInDB = product.get();
        productInDB.setQuantity(productInDB.getQuantity() - productReservedEvent.getQuantity());
        productEntityRepository.save(productInDB);
    }
}
