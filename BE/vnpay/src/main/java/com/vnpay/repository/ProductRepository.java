package com.vnpay.repository;

import com.vnpay.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    /**
     * Retrieves a list of products based on the provided list of product IDs.
     *
     * @param ids A list of integers representing the product IDs to retrieve.
     * @return A list of Product objects that have IDs matching any of the provided IDs.
     */
    List<Product> findByIdIn(List<Integer> ids);
}
