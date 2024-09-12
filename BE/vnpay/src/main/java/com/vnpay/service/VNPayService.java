package com.vnpay.service;

import com.vnpay.dto.request.VNPayRequestDto;
import com.vnpay.dto.response.VNPayResponseDto;

public interface VNPayService {
    /**
     * Creates VNPay parameters based on the provided VNPay request data.
     *
     * @param vnPayDtoRequest The request data containing details needed to generate VNPay parameters.
     * @return A VNPayResponseDto object containing the generated VNPay parameters.
     */
    VNPayResponseDto createVNPayParams(VNPayRequestDto vnPayDtoRequest);
}
