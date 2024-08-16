package com.aalok.WalletService.Repo;

import com.aalok.WalletService.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepo extends JpaRepository<Wallet,Long> {
}
