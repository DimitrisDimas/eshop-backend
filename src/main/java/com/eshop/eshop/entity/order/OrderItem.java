package com.eshop.eshop.entity.order;

import com.eshop.eshop.entity.order.ProductItemOrder;
import jakarta.persistence.*;

import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="OrderItem")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;

    @Embedded
    private ProductItemOrder itemOrdered;
    @Column(name="Price")
    private Long price;
    @Column(name="Quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;
}
