package com.paymybuddy.pochiita.controller;

import com.paymybuddy.pochiita.dto.TransactionAddDTO;
import com.paymybuddy.pochiita.dto.UserDTO;
import com.paymybuddy.pochiita.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/profile/transactionadd" )
    public String transaction_direct_friend (Model model,@RequestParam(value = "f") long friend){
        TransactionAddDTO transactionAddDTO = new TransactionAddDTO();
        model.addAttribute("transaction",transactionAddDTO);
        model.addAttribute("id",friend);
        return "transactionAdd.html";
    }

    @PostMapping("/profile/transaction/add")
    public String transaction_add (Model model, @Valid @ModelAttribute("transaction") TransactionAddDTO transactionAddDTO, BindingResult result, @RequestParam(value = "f") long friend){
        if (result.hasErrors() || result.hasFieldErrors()){
            model.addAttribute("transaction",transactionAddDTO);
            return "transactionAdd.html";
        }

        if (!transactionService.is_transaction_possible(transactionAddDTO.getAmount())){
            return "redirect:/profile/transactionadd?f="+friend+"&amount";

        }

        if(transactionService.create_transaction(friend,transactionAddDTO.getAmount(),transactionAddDTO.getDescription())){
            return "redirect:/profile/transactionadd?f="+friend+"&success";
        }else{
            return "redirect:/profile/transactionadd?f="+friend+"&fail";

        }

    }
}