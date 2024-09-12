package com.vnpay.controller;

import com.vnpay.dto.response.ProductResponseDto;
import com.vnpay.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Tag(name = "Cart", description = "Cart APIs")
public class CartController {

    private final ProductService productService;

    /**
     * Retrieves the list of products in the cart based on the provided product IDs.
     *
     * @param productIds A list of integers representing the product IDs to retrieve.
     * @return ResponseEntity containing the list of ProductResponseDto objects.
     */
    @Operation(summary = "Retrieves the list of products in the cart based on the provided product IDs.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Get cart successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @PostMapping(produces = "application/vnd.vnpayapi.v1+json")
    public ResponseEntity<List<ProductResponseDto>> getCartProductsV1(@RequestBody List<Integer> productIds) {
        return ResponseEntity.ok(productService.getCartProducts(productIds));
    }
}

