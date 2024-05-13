package com.paymybuddy.pochiita.repository;

import com.paymybuddy.pochiita.model.Account;
import com.paymybuddy.pochiita.model.Transaction;
import com.paymybuddy.pochiita.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository  extends JpaRepository<Transaction,Long> {

    @Query("SELECT t FROM Transaction t WHERE debtor.id = :userId OR receiver.id = :userId")
    List<Transaction> findTransactionsByDebtorAndReceiver(@Param("userId") Long userId, Pageable pageable);


}
