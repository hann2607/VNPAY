package com.vnpay.controller;

import com.vnpay.dto.response.OrderResponseDto;
import com.vnpay.dto.response.WalletResponseDto;
import com.vnpay.service.ProductService;
import com.vnpay.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
@Tag(name = "Wallet", description = "Wallet APIs")
public class WalletController {

    private final WalletService walletService;
    private final ProductService productService;

    /**
     * Retrieves the wallet information for the given wallet ID.
     *
     * @param walletId The ID of the wallet to retrieve information for.
     * @return ResponseEntity containing the WalletResponseDto with the wallet details.
     */
    @Operation(summary = "Retrieves the wallet information for the given wallet ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Get wallet information successfully"),
            @ApiResponse(responseCode = "404", description = "Wallet is not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping(path = "/{walletId}", produces = "application/vnd.vnpayapi.v1+json")
    public ResponseEntity<WalletResponseDto> getWalletInfoV1(@PathVariable Integer walletId) {
        return ResponseEntity.ok(walletService.getWalletInfo(walletId));
    }

    /**
     * Retrieves the top 3 newest orders.
     *
     * @return ResponseEntity containing a list of OrderResponseDto objects representing the top 3 newest orders.
     */
    @Operation(summary = "Retrieves the top 3 newest orders.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Get the top 3 newest orders successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping(path = "/orders", produces = "application/vnd.vnpayapi.v1+json")
    public ResponseEntity<List<OrderResponseDto>> getOrdersV1() {
        return ResponseEntity.ok(productService.getTop3NewOrders());
    }
}

