package com.paymybuddy.pochiita.service;

import com.paymybuddy.pochiita.model.Transaction;
import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    public boolean addAFriend(long friend_id) throws Exception {

        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username=((UserDetails) principal).getUsername();
        }

        User user = userRepository.findByEmail(username);
        Optional<User> optional_to_add = userRepository.findById(friend_id);


        if ( user != null && optional_to_add.isPresent()){
            User to_add_verified = optional_to_add.orElseThrow();
            List<User> friendlist = user.getFriendsList();
            friendlist.add(to_add_verified);
            userRepository.save(user);
            return true;
        }else{
            return false;
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
        System.out.println(actual_page);
        System.out.println(max_offset);

        if (actual_page < max_offset){
            available_indexes.put("next",actual_page+1);
        }

        return available_indexes;
    }
}
