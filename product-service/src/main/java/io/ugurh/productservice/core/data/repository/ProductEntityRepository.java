package io.ugurh.productservice.core.data.repository;

import io.ugurh.productservice.core.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findByTitle(String searchValue);
}