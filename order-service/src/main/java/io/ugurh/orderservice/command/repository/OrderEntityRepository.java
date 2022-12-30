package io.ugurh.orderservice.command.repository;

import io.ugurh.orderservice.command.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, String> {
}