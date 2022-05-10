package com.projet_6.pay_my_buddy.JB.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.List;

@Entity(name = "User")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false,name="emailAdress")
    private String email;

    @Column(nullable = false,name="password")
    private String password;

    @Column(nullable = false,name="amountAppAccount")
    private float amountAppAccount;

    @ManyToMany
    private List<User> contacts;

    @OneToMany
    private List<BankAccount> bankAccount;

    @OneToMany
    private List<TransactionApp> transactionUsers;



}
