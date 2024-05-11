package com.paymybuddy.pochiita.service;

import com.paymybuddy.pochiita.model.Transaction;
import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SummaryService {

    @Autowired
    UserRepository userRepository;


    public User summary(Principal principal) throws Exception {
        String userMail = principal.getName();
        User user = userRepository.findByEmail(userMail);
        if(user == null) {
            throw new Exception("Your account can't be found");
        }
        return user;
    }

    public List<Transaction> get_last_transac (User user,int max_transac){
        List<Transaction> list_transactions = new ArrayList<>();
        int index = 0;
        for (Transaction transaction : user.getAccount().getTransactionList().reversed()){
            list_transactions.add(transaction);

            index ++;
            if (index == max_transac){
                break;
            }
        }
        return list_transactions;
    }
}
