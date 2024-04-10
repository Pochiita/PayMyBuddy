package com.paymybuddy.pochiita.service;

import com.paymybuddy.pochiita.dto.UserDTO;
import com.paymybuddy.pochiita.model.Account;
import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.AccountRepository;
import com.paymybuddy.pochiita.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder = new PasswordEncoder() {
        @Override
        public String encode(CharSequence rawPassword) {
            return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
        }
    };

    public Account baseAccountCreation (){
        Account account = new Account();
        account.setBalance(0.0);
        accountRepository.save(account);
        return account;
    }
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

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
