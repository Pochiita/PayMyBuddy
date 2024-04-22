package com.paymybuddy.pochiita.repository;

import com.paymybuddy.pochiita.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository  extends JpaRepository<Transaction,Long> {
}
