package io.ugurh.productservice.query;

import io.ugurh.productservice.core.data.entity.ProductEntity;
import io.ugurh.productservice.core.events.ProductCreatedEvent;
import io.ugurh.productservice.core.data.repository.ProductEntityRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductEventHandler {
    private ProductEntityRepository repository;

    public ProductEventHandler(ProductEntityRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event){
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event,productEntity);
        repository.save(productEntity);
    }
}
