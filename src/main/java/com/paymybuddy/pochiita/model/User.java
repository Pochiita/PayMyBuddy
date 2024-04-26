package com.paymybuddy.pochiita.model;

import com.paymybuddy.pochiita.repository.UserRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column (nullable = false)
    private String firstName;
    @Column (nullable = false)
    private String lastName;
    @Column(nullable = false, length = 60)
    private String password;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @OneToMany
    private List<User> friendsList = new ArrayList<>();
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="person_id", nullable = false)
    private Account account;



}
