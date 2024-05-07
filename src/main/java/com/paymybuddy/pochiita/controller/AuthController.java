package com.paymybuddy.pochiita.controller;

import com.paymybuddy.pochiita.dto.UserDTO;
import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.service.UserAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/signup")
    public String signup(Model model){
        UserDTO user = new UserDTO();
        model.addAttribute("user",user);
        return "signup";
    }


    @PostMapping("/signup")
    public String signUpCreate(Model model, @Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result){
        User existsUser = userAuthService.findUserByEmail(userDTO.getEmail());

        if (existsUser != null) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDTO);
            return "/signup";
        }

        userAuthService.signUp(userDTO);
        return "redirect:/signup?success";

    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }


}
