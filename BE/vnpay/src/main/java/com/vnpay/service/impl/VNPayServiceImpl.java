package com.vnpay.service.impl;

import com.vnpay.config.AppConfig;
import com.vnpay.dto.request.VNPayRequestDto;
import com.vnpay.dto.response.VNPayResponseDto;
import com.vnpay.service.VNPayService;
import com.vnpay.util.VNPayUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VNPayServiceImpl implements VNPayService {

    private final AppConfig appConfig;

    /**
     * Creates VNPay parameters based on the provided request data and generates a payment URL.
     *
     * @param vnPayDtoRequest The VNPay request data transfer object.
     * @return VNPayResponseDto containing the status, message, and generated payment URL.
     */
    @Override
    public VNPayResponseDto createVNPayParams(VNPayRequestDto vnPayDtoRequest) {
        // Generate current and expiration dates for the VNPay request
        String vnpCreateDate = getCurrentFormattedDate(0);
        String vnpExpireDate = getCurrentFormattedDate(15);

        // Set VNPay request parameters
        vnPayDtoRequest.setVnpVersion(appConfig.getVersion());
        vnPayDtoRequest.setVnpCommand(appConfig.getCommandPay());
        vnPayDtoRequest.setVnpTmnCode(appConfig.getTmnCode());
        vnPayDtoRequest.setVnpExpireDate(vnpExpireDate);
        vnPayDtoRequest.setVnpCreateDate(vnpCreateDate);

        // Generate payment URL
        String url = generatePaymentUrl(vnPayDtoRequest);

        // Return the response with a success status and the generated URL
        return new VNPayResponseDto("201", "Successfully created VNPay", url);
    }

    /**
     * Returns the current date formatted as a string with optional minutes to add.
     *
     * @param minutesToAdd Number of minutes to add to the current date.
     * @return Formatted date string.
     */
    private String getCurrentFormattedDate(int minutesToAdd) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        calendar.add(Calendar.MINUTE, minutesToAdd);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(calendar.getTime());
    }

    /**
     * Generates a VNPay payment URL based on the provided request data.
     *
     * @param vnPayDtoRequest The VNPay request data transfer object.
     * @return The generated payment URL.
     */
    private String generatePaymentUrl(VNPayRequestDto vnPayDtoRequest) {
        String hashData = vnPayDtoRequest.toMap().entrySet().stream()
                .filter(entry -> entry.getValue() != null && !entry.getValue().isEmpty())
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> encodeParam(entry.getKey()) + "=" + encodeParam(entry.getValue()))
                .collect(Collectors.joining("&"));

        String vnpSecureHash = VNPayUtil.hmacSHA512(appConfig.getHashSecret(), hashData);

        return appConfig.getPayUrl() + "?" + hashData + "&vnp_SecureHash=" + vnpSecureHash;
    }

    /**
     * URL-encodes a given parameter.
     *
     * @param param The parameter to encode.
     * @return URL-encoded parameter string.
     */
    private String encodeParam(String param) {
        return URLEncoder.encode(param, StandardCharsets.US_ASCII);
    }
}
