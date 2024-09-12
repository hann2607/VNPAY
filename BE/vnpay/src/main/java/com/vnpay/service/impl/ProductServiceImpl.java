package com.vnpay.service.impl;

import com.vnpay.dto.response.OrderResponseDto;
import com.vnpay.dto.response.ProductResponseDto;
import com.vnpay.repository.OrdersRepository;
import com.vnpay.repository.ProductRepository;
import com.vnpay.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final OrdersRepository ordersRepository;

    /**
     * Retrieves a list of products based on the provided product IDs and maps them to a list of ProductResponseDto.
     *
     * @param productIds A list of product IDs to retrieve.
     * @return A list of ProductResponseDto objects representing the retrieved products.
     */
    @Override
    public List<ProductResponseDto> getCartProducts(List<Integer> productIds) {
        // Retrieve products by their IDs and map them to ProductResponseDto
        return productRepository.findByIdIn(productIds).stream()
                .map(product -> {
                    ProductResponseDto dto = new ProductResponseDto();
                    // Copy properties from Product to ProductResponseDto
                    BeanUtils.copyProperties(product, dto);
                    return dto;
                })
                .toList();
    }

    /**
     * Retrieves the top 3 most recent orders and maps them to a list of OrderResponseDto.
     *
     * @return A list of OrderResponseDto objects representing the top 3 most recent orders.
     */
    @Override
    public List<OrderResponseDto> getTop3NewOrders() {
        // Retrieve top 3 new orders and map them to OrderResponseDto
        return ordersRepository.findTop3ByOrderByCreatedDateDesc().stream()
                .map(order -> {
                    OrderResponseDto dto = new OrderResponseDto();
                    // Copy properties from Order to OrderResponseDto
                    BeanUtils.copyProperties(order, dto);
                    return dto;
                })
                .toList();
    }
}
