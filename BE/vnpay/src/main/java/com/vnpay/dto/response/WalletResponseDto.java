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
public class WalletResponseDto implements Serializable {
    private Integer id;
    private String currency;
    private BigDecimal balance;
    private Instant createdDate;
}
