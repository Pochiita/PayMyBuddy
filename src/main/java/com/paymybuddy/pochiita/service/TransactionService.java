package com.paymybuddy.pochiita.service;

import com.paymybuddy.pochiita.dto.TransactionAddDTO;
import com.paymybuddy.pochiita.dto.TransactionCreateDTO;
import com.paymybuddy.pochiita.model.Transaction;
import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.AccountRepository;
import com.paymybuddy.pochiita.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    public boolean is_transaction_possible(double amount){
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username=((UserDetails) principal).getUsername();
        }
        System.out.println(username);
        User user = userRepository.findByEmail(username);

        if (user.getAccount().getBalance() < amount){
            return false;
        }else{
            return true;
        }
    }

    public boolean create_transaction (long receiver_id, double amount,String description ){
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username=((UserDetails) principal).getUsername();
        }

        User user = userRepository.findByEmail(username);
        Optional<User> optional_to_add = userRepository.findById(receiver_id);

        if (user.getAccount().getBalance()<amount){

        }
        if ( user != null && optional_to_add.isPresent()){
            User to_add_verified = optional_to_add.orElseThrow();

            //creating transaction with the received data
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setDescription(description);
            transaction.setReceiver(to_add_verified);
            transaction.setDebtor(user);

            // Adding transaction to both concerned users
            add_transaction_to_user(user,transaction);
            add_transaction_to_user(to_add_verified,transaction);

            //Manage balance for the two users
            manage_balance(to_add_verified,user,amount);
            return true;
        }else{
            return false;
        }
    }



    public void add_transaction_to_user(User user, Transaction transaction){
        List<Transaction> list_transaction = user.getAccount().getTransactionList();
        list_transaction.add(transaction);
        accountRepository.save(user.getAccount());
    }

    public void manage_balance(User receiver,User debiter,double amount){
        double receiver_balance = receiver.getAccount().getBalance();
        double debiter_balance = debiter.getAccount().getBalance();

        receiver.getAccount().setBalance(receiver_balance+(amount*0.95));
        debiter.getAccount().setBalance(debiter_balance-(amount));

        userRepository.save(receiver);
        userRepository.save(debiter);
    }
}
