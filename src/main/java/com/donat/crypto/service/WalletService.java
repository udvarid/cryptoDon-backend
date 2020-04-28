package com.donat.crypto.service;

import com.donat.crypto.domain.Wallet;

public interface WalletService {

	Wallet createWallet(String ccy, double amount);

}
