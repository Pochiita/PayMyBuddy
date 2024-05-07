package com.paymybuddy.pochiita.controller;

import com.paymybuddy.pochiita.dto.TransactionAddDTO;
import com.paymybuddy.pochiita.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransactionController {

    @GetMapping("/profile/transactionadd")
    public String transaction_direct_friend (Model model){
        TransactionAddDTO transactionAddDTO = new TransactionAddDTO();
        model.addAttribute("transaction",transactionAddDTO);
        return "transactionAdd.html";
    }

    @PostMapping("/profile/transaction/add")
    public String transaction_add (Model model, @Valid @ModelAttribute("transaction") TransactionAddDTO transactionAddDTO, BindingResult result){

    }
}
