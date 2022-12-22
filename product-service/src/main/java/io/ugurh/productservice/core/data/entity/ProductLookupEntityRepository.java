package io.ugurh.productservice.core.data.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookupEntityRepository extends JpaRepository<ProductLookupEntity, String> {
    ProductLookupEntity findByProductIdOrTitle(String productId, String title);
}