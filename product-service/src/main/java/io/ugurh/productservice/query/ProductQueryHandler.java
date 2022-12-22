package io.ugurh.productservice.query;

import io.ugurh.productservice.core.data.entity.ProductEntity;
import io.ugurh.productservice.core.data.repository.ProductEntityRepository;
import io.ugurh.productservice.query.model.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductQueryHandler {

    private final ProductEntityRepository repository;

    public ProductQueryHandler(ProductEntityRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductsQuery query) {
        List<ProductRestModel> productRestModels = new ArrayList<>();

        List<ProductEntity> productStored = repository.findAll();

        for (ProductEntity entity : productStored) {
            ProductRestModel model = new ProductRestModel();
            BeanUtils.copyProperties(entity, model);
            productRestModels.add(model);
        }

        return productRestModels;
    }
}
