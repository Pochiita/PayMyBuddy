package com.paymybuddy.pochiita.service;

import com.paymybuddy.pochiita.dto.UserDTO;
import com.paymybuddy.pochiita.model.Account;
import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserLogServices {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public void signUp (UserDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUsername(userDTO.getUsername());
        user.setAccount(baseAccountCreation());
        userRepository.save(user);
    }

    public Account baseAccountCreation (){
        Account account = new Account();
        account.setBalance(0.0);
        return account;
    }
}
