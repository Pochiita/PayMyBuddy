package com.paymybuddy.pochiita.service;

import com.paymybuddy.pochiita.model.Transaction;
import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
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
        List<User> list = userRepository.findAllUsersNotInFriendListAndNotOwnUser(user.getId(),user.getId(),PageRequest.of(page, limit));
        return list;
    }

    public HashMap<String, Integer> handlePagination (int actual_page, int offset, int totalElts){
        int max_offset = (int) Math.ceil(totalElts /offset);
        HashMap<String,Integer> available_indexes = new HashMap<>();
        if (actual_page >0){
           available_indexes.put("prev",actual_page-1);
        }

        available_indexes.put("current",actual_page);

        if (actual_page < max_offset){
            available_indexes.put("next",actual_page+1);
        }

        return available_indexes;
    }
}
