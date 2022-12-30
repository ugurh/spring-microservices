package io.ugurh.orderservice.command.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDERS")
public class OrderEntity {
    @Id
    @Column(unique = true)
    private String orderId;
    private String userId;
    private String productId;
    private String addressId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private Integer quantity;

}
