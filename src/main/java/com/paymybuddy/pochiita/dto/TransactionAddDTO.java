package com.paymybuddy.pochiita.dto;

import com.paymybuddy.pochiita.model.User;
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
public class TransactionAddDTO {
    
    @NotEmpty
    private String description;

    @NotNull
    private double amount;

}