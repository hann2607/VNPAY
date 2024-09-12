package com.vnpay.service.impl;

import com.vnpay.constant.ErrorCodeConstant;
import com.vnpay.dto.request.PayRequestDto;
import com.vnpay.dto.request.VNPayRequestDto;
import com.vnpay.dto.response.VNPayResponseDto;
import com.vnpay.entity.OrderDetail;
import com.vnpay.entity.Orders;
import com.vnpay.entity.PaymentMethod;
import com.vnpay.entity.Wallet;
import com.vnpay.enumeration.Status;
import com.vnpay.exception.DataConflictException;
import com.vnpay.exception.DataNotfoundException;
import com.vnpay.repository.OrderDetailRepository;
import com.vnpay.repository.OrdersRepository;
import com.vnpay.repository.PaymentMethodRepository;
import com.vnpay.repository.ProductRepository;
import com.vnpay.repository.WalletRepository;
import com.vnpay.service.CheckoutService;
import com.vnpay.service.VNPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final OrdersRepository ordersRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final WalletRepository walletRepository;
    private final VNPayService vnPayService;

    /**
     * Processes a checkout request by creating an order and saving it along with its details.
     *
     * @param vnPayDtoRequest The VNPay request data containing order details and product IDs.
     * @return VNPayResponseDto The response containing VNPay parameters for further processing.
     * @throws DataNotfoundException If the product is not found.
     */
    @Override
    public VNPayResponseDto checkout(VNPayRequestDto vnPayDtoRequest) {
        // Create a new order from the request data
        Orders order = new Orders();
        order.setOrderId(vnPayDtoRequest.getOrderId());
        order.setStatus(vnPayDtoRequest.getStatus());

        // Convert the amount from VNPAY's format to BigDecimal
        BigDecimal amount = new BigDecimal(vnPayDtoRequest.getVnpAmount())
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        order.setTotalAmount(amount);
        order.setCreatedDate(Instant.now());

        // Save the order to the database
        Orders savedOrder = ordersRepository.save(order);

        // Create and save order details for each product ID in the request
        List<OrderDetail> orderDetailList = vnPayDtoRequest.getProductIds().stream()
                .map(productId -> productRepository.findById(productId)
                        .map(product -> {
                            // Create order detail for the product
                            OrderDetail orderDetail = new OrderDetail();
                            orderDetail.setOrder(savedOrder);
                            orderDetail.setProduct(product);
                            orderDetail.setQuantity(1); // Default quantity
                            orderDetail.setCreatedDate(Instant.now());
                            return orderDetail;
                        })
                        .orElseThrow(() -> new DataNotfoundException(ErrorCodeConstant
                                .getErrorCode(ErrorCodeConstant.NOT_FOUND, "product")))
                ).toList();

        // Save all order details to the database
        orderDetailRepository.saveAll(orderDetailList);

        // Generate and return the VNPay response based on the request data
        return vnPayService.createVNPayParams(vnPayDtoRequest);
    }


    /**
     * Handles the re-checkout process by updating the creation date of an existing order
     * and generating the VNPay response parameters.
     *
     * @param vnPayDtoRequest The request data transfer object containing the order ID and other details.
     * @return VNPayResponseDto The response data transfer object containing VNPay parameters.
     */
    @Override
    public VNPayResponseDto reCheckout(VNPayRequestDto vnPayDtoRequest) {
        // Retrieve the order by its ID
        Orders order = ordersRepository.findByOrderId(vnPayDtoRequest.getOrderId())
                .orElse(null);

        // If the order exists, update its creation date and save it
        if (order != null) {
            order.setCreatedDate(Instant.now());
            ordersRepository.save(order);
        }

        // Generate and return the VNPay response parameters
        return vnPayService.createVNPayParams(vnPayDtoRequest);
    }


    /**
     * Processes a payment request by updating the payment method, order status, and wallet balance.
     *
     * @param payDtoRequest The payment request data transfer object containing payment details.
     * @throws DataNotfoundException If the order or wallet is not found.
     * @throws DataConflictException If there is a data conflict, such as a duplicate payment method.
     */
    @Override
    public void pay(PayRequestDto payDtoRequest) {
        try {
            // Create and save the payment method
            PaymentMethod paymentMethod = new PaymentMethod();
            BeanUtils.copyProperties(payDtoRequest, paymentMethod);
            paymentMethod.setName("VNPAY");
            paymentMethod.setCreatedDate(Instant.now());
            paymentMethodRepository.save(paymentMethod);

            // Retrieve and update the order with the payment method and status
            Orders order = ordersRepository.findByOrderId(payDtoRequest.getVnpOrderInfo())
                    .orElseThrow(() -> new DataNotfoundException(ErrorCodeConstant
                            .getErrorCode(ErrorCodeConstant.NOT_FOUND, "order")));

            order.setPaymentMethod(paymentMethod);
            order.setStatus("00".equals(payDtoRequest.getVnpTransactionStatus()) ? Status.COMPLETED : Status.PENDING);
            ordersRepository.save(order);

            // Update the wallet balance
            Wallet wallet = walletRepository.findAll().stream().findFirst()
                    .orElseThrow(() -> new DataNotfoundException(ErrorCodeConstant
                            .getErrorCode(ErrorCodeConstant.NOT_FOUND, "wallet")));

            BigDecimal amount = new BigDecimal(payDtoRequest.getVnpAmount())
                    .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

            wallet.setBalance(wallet.getBalance().compareTo(amount) >= 0
                    ? wallet.getBalance().subtract(amount)
                    : BigDecimal.ZERO);

            walletRepository.save(wallet);
        } catch (DataIntegrityViolationException e) {
            throw new DataConflictException(ErrorCodeConstant
                    .getErrorCode(ErrorCodeConstant.INVALID_EXISTED, "paymentMethod"));
        }
    }
}
