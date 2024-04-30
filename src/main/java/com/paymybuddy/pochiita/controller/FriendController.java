package com.paymybuddy.pochiita.controller;

import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.UserRepository;
import com.paymybuddy.pochiita.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class FriendController {

    @Autowired
    FriendsService friendsService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/profile/friendslist")
    public String friendsList (Principal principal, Model model,@RequestParam(value = "limit") int limit,@RequestParam(value = "page") int page){
        try {
            User user = friendsService.getUser(principal);
            List<User> avalaible_friends = friendsService.listOfAvailableFriend(user,page,limit);
            model.addAttribute("user", user);
            model.addAttribute("available_friends",avalaible_friends);
            return "friendsList";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
