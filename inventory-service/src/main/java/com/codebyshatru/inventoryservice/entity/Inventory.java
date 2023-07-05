package com.codebyshatru.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory", uniqueConstraints = @UniqueConstraint(columnNames = "sku_code", name = "sku_code"))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sku_code")
    private String skuCode;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "created_dt", nullable = false)
    private LocalDateTime createdDt;
    @Column(name = "updated_dt", nullable = true)
    private LocalDateTime updatedDt;
    @Column(name = "deleted_dt", nullable = true)
    private LocalDateTime deletedDt;
}
