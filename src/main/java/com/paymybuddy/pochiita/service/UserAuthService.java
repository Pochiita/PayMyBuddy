package com.paymybuddy.pochiita.service;

import com.paymybuddy.pochiita.dto.UserDTO;
import com.paymybuddy.pochiita.model.Account;
import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.AccountRepository;
import com.paymybuddy.pochiita.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserAuthService {
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
    /**
     * Methods that creates the account that will be linked to a user in db
     *
     */
    public Account baseAccountCreation (){
        Account account = new Account();
        account.setBalance(0.0);
        accountRepository.save(account);
        return account;
    }

    /**
     * Methods that creates the person object in db
     * @param userDTO
     */
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

    public boolean checkLogin(String email, String password){
            // TODO Auto-generated method stub
            User user = userRepository.findByEmail(email);
            if (user != null) {
                if (passwordEncoder.matches(password, user.getPassword())) {
                    return true;
                }
            }
            return false;

    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
