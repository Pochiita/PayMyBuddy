package com.paymybuddy.pochiita.service;

import com.paymybuddy.pochiita.dto.TransactionAddDTO;
import com.paymybuddy.pochiita.dto.TransactionCreateDTO;
import com.paymybuddy.pochiita.model.Transaction;
import com.paymybuddy.pochiita.model.User;
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

    public void get_transaction_dto (Optional<TransactionAddDTO> transactionAddDTO,Optional<TransactionCreateDTO> transactionCreateDTO){
        if (transactionAddDTO.isPresent()){
            TransactionAddDTO transaction_data = transactionAddDTO.get();
        }

        if (transactionCreateDTO.isPresent()){
            TransactionCreateDTO transaction_data = transactionCreateDTO.get();

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

        if ( user != null && optional_to_add.isPresent()){
            User to_add_verified = optional_to_add.orElseThrow();
            Transaction transaction = new Transaction();
            return true;
        }else{
            return false;
        }
    }



    public void add_transaction_to_user(User user, Transaction transaction){

    }
}
