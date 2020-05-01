package com.donat.crypto.service.Implementation;

import com.donat.crypto.domain.Wallet;
import com.donat.crypto.repository.WalletRepository;
import com.donat.crypto.service.WalletService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class WalletServiceImpl implements WalletService {

    private WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(String ccy, double amount) {
        Wallet wallet = new Wallet(ccy, amount);
        return walletRepository.saveAndFlush(wallet);
    }

    //TODO optimistic lock-ot bekapcsolni a wallet repo-ban és az event repoban
}
