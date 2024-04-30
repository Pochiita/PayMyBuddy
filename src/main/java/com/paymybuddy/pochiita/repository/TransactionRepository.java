package com.paymybuddy.pochiita.repository;

import com.paymybuddy.pochiita.model.Account;
import com.paymybuddy.pochiita.model.Transaction;
import com.paymybuddy.pochiita.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository  extends JpaRepository<Transaction,Long> {

    @Query("SELECT t FROM Transaction t WHERE t.debtor.id = :userId AND t.receiver.id = :userId")
    List<Transaction> findTransactionsByDebtorAndReceiver(Long userId);

}