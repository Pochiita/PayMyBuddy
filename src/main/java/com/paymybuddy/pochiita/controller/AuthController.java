package com.paymybuddy.pochiita.controller;

import com.paymybuddy.pochiita.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/signup")
    public String signup(Model model){
        UserDTO user = new UserDTO();
        model.addAttribute("user",user);
        return "signup";
    }



}
