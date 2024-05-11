package com.paymybuddy.pochiita.controller;

import com.paymybuddy.pochiita.model.Account;
import com.paymybuddy.pochiita.model.Transaction;
import com.paymybuddy.pochiita.model.User;
import com.paymybuddy.pochiita.repository.AccountRepository;
import com.paymybuddy.pochiita.repository.TransactionRepository;
import com.paymybuddy.pochiita.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class SummaryController {

    @Autowired
    SummaryService summaryService;

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/profile/summary")
    public String summary (Principal principal,Model model){
        try {
            User user = summaryService.summary(principal);
            List<Transaction>  transactionList = summaryService.get_last_transac(user,5);
            model.addAttribute("user", user);
            model.addAttribute("transactions",transactionList);

            return "summary";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
