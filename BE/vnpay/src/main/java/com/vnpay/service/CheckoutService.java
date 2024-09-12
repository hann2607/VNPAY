package com.vnpay.service;

import com.vnpay.dto.request.PayRequestDto;
import com.vnpay.dto.request.VNPayRequestDto;
import com.vnpay.dto.response.VNPayResponseDto;

public interface CheckoutService {
    /**
     * Processes a checkout request with the provided VNPay request data.
     *
     * @param vnPayDtoRequest A VNPayRequestDto object containing the details of the checkout request.
     * @return A VNPayResponseDto object containing the response data of the checkout operation.
     */
    VNPayResponseDto checkout(VNPayRequestDto vnPayDtoRequest);

    /**
     * Processes a re-checkout request with the provided VNPay request data.
     *
     * @param vnPayDtoRequest A VNPayRequestDto object containing the details of the re-checkout request.
     * @return A VNPayResponseDto object containing the response data of the re-checkout operation.
     */
    VNPayResponseDto reCheckout(VNPayRequestDto vnPayDtoRequest);

    /**
     * Processes a payment request with the provided payment request data.
     *
     * @param payDtoRequest A PayRequestDto object containing the details of the payment request.
     */
    void pay(PayRequestDto payDtoRequest);
}
