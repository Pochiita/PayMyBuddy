package com.paymybuddy.pochiita.controller;

import com.paymybuddy.pochiita.dto.UserDTO;
import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.service.UserAuthServices;
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
    private UserAuthServices userAuthServices;

    @GetMapping("/signup")
    public String signup(Model model){
        UserDTO user = new UserDTO();
        model.addAttribute("user",user);
        return "signup";
    }


    @PostMapping("/signup")
    public String signUpCreate(Model model, @Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result){
        User existsUser = userAuthServices.findUserByEmail(userDTO.getEmail());

        if (existsUser != null) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        System.out.println(userDTO.getEmail());
        System.out.println(result.getFieldError());

        if(result.hasErrors()){
            model.addAttribute("user", userDTO);
            return "/signup";
        }

        userAuthServices.signUp(userDTO);
        return "redirect:/signup?success";

    }



}
