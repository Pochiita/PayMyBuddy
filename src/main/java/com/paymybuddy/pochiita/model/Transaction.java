package com.paymybuddy.pochiita.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User debtor;

    @ManyToOne
    private User receiver;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Account account;
}
