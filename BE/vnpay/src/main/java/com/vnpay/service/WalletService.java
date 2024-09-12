package com.vnpay.service;

import com.vnpay.dto.response.WalletResponseDto;

public interface WalletService {
    /**
     * Retrieves the wallet information for a given wallet ID.
     *
     * @param walletId The ID of the wallet whose information is to be retrieved.
     * @return A WalletResponseDto object containing the wallet information.
     */
    WalletResponseDto getWalletInfo(Integer walletId);
}
