package com.aalok.TxnService.Repo;

import com.aalok.TxnService.model.Txn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnRepo extends JpaRepository<Txn,Integer> {
}
