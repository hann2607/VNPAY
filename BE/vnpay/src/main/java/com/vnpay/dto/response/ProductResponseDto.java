package com.vnpay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer discount;
    private String image;
    private Boolean available;
    private Instant createdDate;
}
