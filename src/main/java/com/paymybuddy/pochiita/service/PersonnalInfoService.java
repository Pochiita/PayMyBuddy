package com.paymybuddy.pochiita.service;

import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import java.security.Principal;
@Service
public class PersonnalInfoService {

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
