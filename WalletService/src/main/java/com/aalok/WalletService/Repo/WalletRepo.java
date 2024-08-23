package com.aalok.WalletService.Repo;

import com.aalok.WalletService.model.Wallet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WalletRepo extends JpaRepository<Wallet,Long> {
    Wallet findByContact(String contact);
    @Transactional
    @Modifying
    @Query("update wallet w set w.balance = w.balance+ :amonunt where w.contact= :contact")
    void updateWallet(String contact, double amount);
}
