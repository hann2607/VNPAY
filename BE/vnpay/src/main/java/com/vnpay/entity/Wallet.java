package com.vnpay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@SuperBuilder
@Data
@Entity
@Table
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String currency;

    @Column(nullable = false, precision = 20, scale = 2)
    private BigDecimal balance;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate;
}
