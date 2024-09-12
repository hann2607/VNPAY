package com.vnpay.service;

import com.vnpay.dto.response.OrderResponseDto;
import com.vnpay.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    /**
     * Retrieves a list of product response data based on the provided list of product IDs.
     *
     * @param productIds A list of integers representing the IDs of the products to retrieve.
     * @return A list of ProductResponseDto objects containing the details of the requested products.
     */
    List<ProductResponseDto> getCartProducts(List<Integer> productIds);

    /**
     * Retrieves the top 3 most recent orders based on their creation date.
     *
     * @return A list of OrderResponseDto objects representing the top 3 most recent orders.
     */
    List<OrderResponseDto> getTop3NewOrders();
}
