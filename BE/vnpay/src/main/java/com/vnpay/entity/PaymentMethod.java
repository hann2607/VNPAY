package com.vnpay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@NoArgsConstructor
@SuperBuilder
@Data
@Entity
@Table
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(name = "vnp_bank_code", length = 10)
    private String vnpBankCode;

    @Column(name = "vnp_bank_tran_no", length = 20)
    private String vnpBankTranNo;

    @Column(name = "vnp_card_type", length = 10)
    private String vnpCardType;

    @Column(name = "vnp_order_info", length = 255)
    private String vnpOrderInfo;

    @Column(name = "vnp_pay_date", length = 20)
    private String vnpPayDate;

    @Column(name = "vnp_response_code", length = 10)
    private String vnpResponseCode;

    @Column(name = "vnp_tmn_code", length = 20)
    private String vnpTmnCode;

    @Column(name = "vnp_transaction_no", length = 20)
    private String vnpTransactionNo;

    @Column(name = "vnp_transaction_status", length = 10)
    private String vnpTransactionStatus;

    @Column(name = "vnp_txn_ref", length = 20, unique = true)
    private String vnpTxnRef;

    @Column(name = "vnp_secure_hash", length = 255)
    private String vnpSecureHash;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @OneToOne(mappedBy = "paymentMethod")
    private Orders order;
}
