package com.paymybuddy.pochiita.dto;

import com.paymybuddy.pochiita.model.Account;
import com.paymybuddy.pochiita.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    @NotEmpty
    private User debtor;

    @NotEmpty
    private User receiver;

    @NotEmpty
    private String description;

    @NotEmpty
    private double amount;

}
