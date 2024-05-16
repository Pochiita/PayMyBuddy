package com.paymybuddy.pochiita.dto;

import com.paymybuddy.pochiita.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @Positive
    private double amount;

}
