package com.paymybuddy.pochiita.controller;

import com.paymybuddy.pochiita.dto.UserDTO;
import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.service.UserAuthServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

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

        if(result.hasErrors()){
            model.addAttribute("user", userDTO);
            return "/signup";
        }

        userAuthServices.signUp(userDTO);
        return "redirect:/signup?success";

    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping ("/checklogin")
    public RedirectView loginPost(HttpServletRequest request, @RequestParam("mail") String mail, @RequestParam("password") String password, RedirectAttributes redirectAttributes){
        boolean isCheckable = userAuthServices.checkLogin(mail,password);
        if (isCheckable) {
            if (SecurityContextHolder.getContext().getAuthentication() == null ||
                    SecurityContextHolder.getContext()
                            .getAuthentication().getClass().equals(AnonymousAuthenticationToken.class)) {
                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(mail, password,new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(token);
            }
            redirectAttributes.addFlashAttribute("message", "Login Successful");
            return new RedirectView("hello");

        }
        redirectAttributes.addFlashAttribute("message", "Invalid Username or Password");
        return new RedirectView("login");
    }
}
