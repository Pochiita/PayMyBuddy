package com.paymybuddy.pochiita.service;

import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
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
}