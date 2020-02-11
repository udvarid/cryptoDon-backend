package com.donat.crypto.service;

import com.donat.crypto.domain.Wallet;
import com.donat.crypto.repository.WalletRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class WalletService {

    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(String ccy, double amount) {
        Wallet wallet = new Wallet(ccy, amount);
        walletRepository.saveAndFlush(wallet);
        return wallet;
    }
}
