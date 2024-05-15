package com.paymybuddy.pochiita.service;

import com.paymybuddy.pochiita.dto.TransactionAddBalanceDTO;
import com.paymybuddy.pochiita.model.Transaction;
import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.AccountRepository;
import com.paymybuddy.pochiita.repository.TransactionRepository;
import com.paymybuddy.pochiita.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public boolean is_transaction_possible(double amount){

        User user =get_connected_user();

        if (user.getAccount().getBalance() < amount){
            return false;
        }else{
            return true;
        }
    }

    public boolean create_transaction (long receiver_id, double amount,String description ){
             User user =get_connected_user();
        Optional<User> optional_to_add = userRepository.findById(receiver_id);

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

        receiver.getAccount().setBalance(receiver_balance+(amount*0.995));
        debiter.getAccount().setBalance(debiter_balance-(amount));

        userRepository.save(receiver);
        userRepository.save(debiter);
    }

    public boolean add_money(TransactionAddBalanceDTO transactionAddBalanceDTO){
        User user = get_connected_user();
        if (user != null){
            Transaction transaction = new Transaction();
            transaction.setAmount(transactionAddBalanceDTO.getAmount());
            transaction.setDescription("Added credit " + LocalDate.now());
            transaction.setReceiver(user);
            transaction.setDebtor(null);

            add_transaction_to_user(user,transaction);

            user.getAccount().setBalance(user.getAccount().getBalance()+(transactionAddBalanceDTO.getAmount()*0.995));
            userRepository.save(user);

            return true;
        }else{
            return false;
        }
    }

    public boolean receive_money(TransactionAddBalanceDTO transactionAddBalanceDTO){


        User user = get_connected_user();
        if (user != null){
            Transaction transaction = new Transaction();
            transaction.setAmount(transactionAddBalanceDTO.getAmount());
            transaction.setDescription("Withdrawn credit " + LocalDate.now());
            transaction.setReceiver(null);
            transaction.setDebtor(user);

            add_transaction_to_user(user,transaction);

            user.getAccount().setBalance(user.getAccount().getBalance()-transactionAddBalanceDTO.getAmount());
            userRepository.save(user);

            return true;
        }else{
            return false;
        }
    }

    public List<Transaction> get_all_transactions (int offset,int page){

        User user = get_connected_user();
        return transactionRepository.findTransactionsByDebtorAndReceiver(user.getId(), PageRequest.of(page, offset));
    }

    public HashMap<String, Integer> handlePagination (int actual_page, int offset, int totalElts){
        int max_pages = (int) Math.ceil((double)totalElts /(double)offset);
        System.out.println(totalElts);
        System.out.println(offset);
        System.out.println(totalElts/offset);
        System.out.println(max_pages);
        HashMap<String,Integer> available_indexes = new HashMap<>();
        if (actual_page >0){
            available_indexes.put("prev",actual_page-1);
        }

        available_indexes.put("current",actual_page);

        if (actual_page+1 < max_pages){
            available_indexes.put("next",actual_page+1);
        }
        return available_indexes;
    }

    public User get_connected_user (){
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username=((UserDetails) principal).getUsername();
        }

        return userRepository.findByEmail(username);
    }
}
