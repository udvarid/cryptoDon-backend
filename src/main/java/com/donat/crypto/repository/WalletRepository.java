package com.donat.crypto.repository;

import com.donat.crypto.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WalletRepository extends JpaRepository<Wallet, Long> {

	//@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
}
