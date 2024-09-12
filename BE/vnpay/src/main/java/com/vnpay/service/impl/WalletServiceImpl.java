package com.vnpay.service.impl;

import com.vnpay.constant.ErrorCodeConstant;
import com.vnpay.dto.response.WalletResponseDto;
import com.vnpay.entity.Wallet;
import com.vnpay.exception.DataNotfoundException;
import com.vnpay.repository.WalletRepository;
import com.vnpay.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    /**
     * Retrieves the wallet information for the specified wallet ID.
     *
     * @param walletId The ID of the wallet to retrieve.
     * @return WalletResponseDto containing the wallet information.
     * @throws DataNotfoundException If no wallet is found with the provided ID.
     */
    @Override
    public WalletResponseDto getWalletInfo(Integer walletId) {
        // Find the wallet by ID or throw an exception if not found
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new DataNotfoundException(
                        ErrorCodeConstant.getErrorCode(ErrorCodeConstant.NOT_FOUND, "wallet")));

        // Map the wallet entity to the response DTO
        WalletResponseDto walletDtoResponse = new WalletResponseDto();
        BeanUtils.copyProperties(wallet, walletDtoResponse);

        // Return the response DTO with wallet information
        return walletDtoResponse;
    }
}
