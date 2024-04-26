package com.paymybuddy.pochiita.service;

import com.paymybuddy.pochiita.model.Transaction;
import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class FriendsService {

    @Autowired
    UserRepository userRepository;

    public User getUser(Principal principal) throws Exception {
        String userMail = principal.getName();
        User user = userRepository.findByEmail(userMail);
        if(user == null) {
            throw new Exception("Your account can't be found");
        }
        return user;
    }
    public void addAFriend(User baseUser,User toBeAdded) throws Exception {
        List<User> friendsList = baseUser.getFriendsList();
        if (!friendsList.contains(baseUser)) {
            friendsList.add(toBeAdded);
            userRepository.save(baseUser);
        }else{
            throw new Exception("L'utilisateur est déja dans votre liste d'amis");
        }
    }

    public void removeAFriend(User baseUser, User toBeRemoved) throws Exception {
        List<User> friendsList = baseUser.getFriendsList();
        if (friendsList.contains(toBeRemoved)) {
            friendsList.remove(toBeRemoved);
            userRepository.save(baseUser);
        }else{
            throw new Exception("L'utilisateur n'est pas dans votre liste d'amis");
        }
    }

    public List<User> listOfAvailableFriend(User user,int page, int limit){
        Page<User> AllUsers = userRepository.findAll(PageRequest.of(page, limit));

    }
}
