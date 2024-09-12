package com.vnpay.dto.request;

import com.vnpay.constant.ErrorCodeConstant;
import com.vnpay.enumeration.Status;
import com.vnpay.validator.enums.ValidateEnumValue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VNPayRequestDto {
    // VnPay
    private String vnpVersion;
    private String vnpCommand;
    private String vnpTmnCode;
    private String vnpCreateDate;
    private String vnpExpireDate;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 14, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpAmount;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 20, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpCurrCode;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 20, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpTxnRef;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 255, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpOrderInfo;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 10, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpLocale;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 255, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpReturnUrl;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 20, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpIpAddr;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 20, message = ErrorCodeConstant.INVALID_LENGTH)
    private String vnpOrderType;

    // Order
    @ValidateEnumValue(enumClass = Status.class)
    private Status status;

    @NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, max = 10, message = ErrorCodeConstant.INVALID_LENGTH)
    private String orderId;

    @NotEmpty(message = ErrorCodeConstant.REQUIRED_VALIDATE)
    @Size(min = 1, message = ErrorCodeConstant.REQUIRED_VALIDATE)
    private List<@NotNull(message = ErrorCodeConstant.REQUIRED_VALIDATE) Integer> productIds;

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("vnp_Version", vnpVersion);
        map.put("vnp_Command", vnpCommand);
        map.put("vnp_TmnCode", vnpTmnCode);
        map.put("vnp_Amount", vnpAmount);
        map.put("vnp_CurrCode", vnpCurrCode);
        map.put("vnp_TxnRef", vnpTxnRef);
        map.put("vnp_OrderInfo", vnpOrderInfo);
        map.put("vnp_Locale", vnpLocale);
        map.put("vnp_ReturnUrl", vnpReturnUrl);
        map.put("vnp_IpAddr", vnpIpAddr);
        map.put("vnp_OrderType", vnpOrderType);
        map.put("vnp_CreateDate", vnpCreateDate);
        map.put("vnp_ExpireDate", vnpExpireDate);
        return map;
    }
}
