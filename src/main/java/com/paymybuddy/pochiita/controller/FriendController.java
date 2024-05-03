package com.paymybuddy.pochiita.controller;

import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.UserRepository;
import com.paymybuddy.pochiita.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class FriendController {

    @Autowired
    FriendsService friendsService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/profile/friends")
    public String friends (Principal principal, Model model){
        try {
            User user = friendsService.getUser(principal);
            model.addAttribute("user", user);

            return "friends";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/profile/friendslist")
    public String friendsList (Principal principal, Model model,@RequestParam(value = "limit") int limit,@RequestParam(value = "page") int page){
        try {
            User user = friendsService.getUser(principal);
            List<User> avalaible_friends = friendsService.listOfAvailableFriend(user,page,limit);
            model.addAttribute("user", user);
            model.addAttribute("available_friends",avalaible_friends);
            model.addAttribute("pagination",friendsService.handlePagination(page,limit, avalaible_friends.size()
            ));
            return "friendsList";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/profile/friendslist/add")
    public String addingAFriend (@RequestParam(value="f") long friend_id) throws Exception {
        Boolean response = friendsService.addAFriend(friend_id);

        if (response){
            return "redirect:/profile/friendslist?success&page=0&limit=10";

        }else{
            return "redirect:/profile/friendslist?fail&page=0&limit=10";

        }
    }

    @GetMapping("/profile/friendslist/remove")
    public String revovingAFriend (@RequestParam(value="f") long friend_id) throws Exception {
        Boolean response = friendsService.removeAFriend(friend_id);

        if (response){
            return "redirect:/profile/friends?success";

        }else{
            return "redirect:/profile/friends?fail";

        }
    }
}
