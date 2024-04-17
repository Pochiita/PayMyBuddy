package com.paymybuddy.pochiita.service;

import com.github.javafaker.Faker;
import com.paymybuddy.pochiita.dto.UserDTO;
import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class FakeDataGenerator {

    @Autowired
    private UserAuthServices userAuthServices;

    @Autowired
    private UserRepository userRepository;

    Faker faker = new Faker(new Locale("fr-FR"));

    @PostConstruct
    public void generateData (){
        Integer nbrUsers  = Math.toIntExact(userRepository.count());

        User user = userRepository.findByEmail("test@test.com");
        System.out.println(user.getAccount().getBalance());
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

    }


}
