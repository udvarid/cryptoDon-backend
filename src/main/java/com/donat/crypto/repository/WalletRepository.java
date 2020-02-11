package com.donat.crypto.repository;

import com.donat.crypto.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
