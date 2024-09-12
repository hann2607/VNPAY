package com.vnpay.dto.response;

import com.vnpay.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto implements Serializable {
    private Integer id;
    private String orderId;
    private BigDecimal totalAmount;
    private Status status;
    private Instant createdDate;
    private List<Integer> productIdList;
}
