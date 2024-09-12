package com.vnpay.repository;

import com.vnpay.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    /**
     * Finds an order by its order ID.
     *
     * @param orderId The ID of the order to find.
     * @return An Optional containing the Orders object if found, or empty if not found.
     */
    Optional<Orders> findByOrderId(String orderId);

    /**
     * Retrieves the top 3 orders sorted by creation date in descending order.
     *
     * @return A list of the top 3 Orders objects, ordered by their creation date in descending order.
     */
    List<Orders> findTop3ByOrderByCreatedDateDesc();
}
