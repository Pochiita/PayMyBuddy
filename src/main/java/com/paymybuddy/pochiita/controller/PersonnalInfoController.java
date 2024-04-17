package com.paymybuddy.pochiita.controller;

import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.service.PersonnalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PersonnalInfoController {

    @Autowired
    PersonnalInfoService personnalInfoService;

    @GetMapping("/summary")
    public String summary (Principal principal,Model model){
        try {
            User user = personnalInfoService.summary(principal);
            model.addAttribute("user", user);
            return "summary";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
