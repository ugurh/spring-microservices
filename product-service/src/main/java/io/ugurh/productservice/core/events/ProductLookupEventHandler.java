package io.ugurh.productservice.core.events;

import io.ugurh.productservice.core.data.entity.ProductLookupEntity;
import io.ugurh.productservice.core.data.entity.ProductLookupEntityRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventHandler {

    private final ProductLookupEntityRepository repository;

    public ProductLookupEventHandler(ProductLookupEntityRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductLookupEntity entity = new ProductLookupEntity(event.getProductId(), event.getTitle());

        repository.save(entity);
    }
}
