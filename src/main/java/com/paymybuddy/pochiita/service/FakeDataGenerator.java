package com.paymybuddy.pochiita.service;

import com.github.javafaker.Faker;
import com.paymybuddy.pochiita.dto.TransactionDTO;
import com.paymybuddy.pochiita.dto.UserDTO;
import com.paymybuddy.pochiita.model.Transaction;
import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.TransactionRepository;
import com.paymybuddy.pochiita.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
@Transactional
public class FakeDataGenerator {

    @Autowired
    private UserAuthServices userAuthServices;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    Faker faker = new Faker(new Locale("fr-FR"));

    @PostConstruct
    public void generateData (){
        Integer nbrUsers  = Math.toIntExact(userRepository.count());

        User user = userRepository.findByEmail("test@test.com");
        User coming_user = userRepository.findByEmail("test1@test.com");
        if (nbrUsers < 10){
            for(int i = 0;i<10-nbrUsers;i++){
                UserDTO userDTO = new UserDTO();
                userDTO.setFirstName(faker.address().firstName());
                userDTO.setLastName(faker.address().lastName());
                userDTO.setEmail(faker.internet().safeEmailAddress());
                userDTO.setPassword(faker.internet().password());
                userDTO.setUsername(faker.pokemon().name());
                userAuthServices.signUp(userDTO);
            }
        }

        List<Transaction> transactionList = user.getAccount().getTransactionList();
        System.out.println(transactionList);

            Transaction transaction = new Transaction();
            transaction.setDescription(faker.rickAndMorty().quote());
            transaction.setDebtor(coming_user);
            transaction.setReceiver(user);
            transaction.setAmount(1.00);
            transaction.setAccount(user.getAccount());
            transactionRepository.save(transaction);


    }


}
