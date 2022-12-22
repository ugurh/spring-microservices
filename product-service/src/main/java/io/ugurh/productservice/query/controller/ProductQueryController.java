package io.ugurh.productservice.query.controller;

import io.ugurh.productservice.query.FindProductsQuery;
import io.ugurh.productservice.query.model.ProductRestModel;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private final QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getProducts() {
        FindProductsQuery query = new FindProductsQuery();
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
    }
}
