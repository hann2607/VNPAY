package com.vnpay.controller;

import com.vnpay.dto.request.PayRequestDto;
import com.vnpay.dto.request.VNPayRequestDto;
import com.vnpay.dto.response.VNPayResponseDto;
import com.vnpay.service.CheckoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Checkout", description = "Checkout APIs")
public class CheckoutController {

    private final CheckoutService checkoutService;

    /**
     * Processes the checkout request using the provided VNPayRequestDto.
     *
     * @param vnPayDtoRequest The VNPayRequestDto containing the necessary details for the checkout process.
     * @return ResponseEntity containing the VNPayResponseDto with the response from the checkout process.
     */
    @Operation(summary = "Processes the checkout request using the provided VNPayRequestDto.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Processes the checkout successfully"),
            @ApiResponse(responseCode = "400", description = "Input wrong format"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping(path = "/checkout", produces = "application/vnd.vnpayapi.v1+json")
    public ResponseEntity<VNPayResponseDto> checkoutV1(@RequestBody @Valid VNPayRequestDto vnPayDtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(checkoutService.checkout(vnPayDtoRequest));
    }

    /**
     * Processes the re-checkout request for an existing order using the provided VNPayRequestDto.
     *
     * @param vnPayDtoRequest The VNPayRequestDto containing the necessary details for the re-checkout process.
     * @return ResponseEntity containing the VNPayResponseDto with the response from the re-checkout process.
     */
    @Operation(summary = "Processes the re-checkout request for an existing order using the provided VNPayRequestDto.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Processes the re-checkout successfully"),
            @ApiResponse(responseCode = "400", description = "Input wrong format"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping(path = "/recheckout", produces = "application/vnd.vnpayapi.v1+json")
    public ResponseEntity<VNPayResponseDto> reCheckoutV1(@RequestBody @Valid VNPayRequestDto vnPayDtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(checkoutService.reCheckout(vnPayDtoRequest));
    }

    /**
     * Processes the payment for an order using the provided PayRequestDto.
     *
     * @param payDtoRequest The PayRequestDto containing the necessary details to complete the payment.
     * @return ResponseEntity indicating that the payment was processed successfully.
     */
    @Operation(summary = "Processes the payment for an order using the provided PayRequestDto.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Processes the payment successfully"),
            @ApiResponse(responseCode = "400", description = "Input wrong format"),
            @ApiResponse(responseCode = "404", description = "Order or wallet is not found"),
            @ApiResponse(responseCode = "409", description = "There is a data conflict"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping(path = "/checkout/pay", produces = "application/vnd.vnpayapi.v1+json")
    public ResponseEntity<Void> payV1(@RequestBody @Valid PayRequestDto payDtoRequest) {
        checkoutService.pay(payDtoRequest);
        return ResponseEntity.ok().build();
    }
}

