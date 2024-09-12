package com.vnpay.dto.request;

import com.vnpay.constant.ErrorCodeConstant;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayRequestDto {
    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 14, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpAmount;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 10, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpBankCode;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 20, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpBankTranNo;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 10, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpCardType;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 255, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpOrderInfo;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 20, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpPayDate;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 10, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpResponseCode;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 20, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpTmnCode;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 20, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpTransactionNo;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 10, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpTransactionStatus;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 20, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpTxnRef;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 255, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpSecureHash;
}
