package com.projet_6.pay_my_buddy.JB.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name="user_id")
    private Long id;

    @Column(nullable = false,name="emailAdress")
    private String email;

    @Column(nullable = false,name="password")
    private String password;

    @Column(nullable = false,name="amountAppAccount")
    private float amountAppAccount;

    @ManyToMany
    @Column(nullable = false,name="assoc_user_user_id")
    private List<User> contacts;

    @OneToMany
    @Column(nullable = false,name="bank_account_id")
    private List<BankAccount> bankAccount;

    @OneToMany
    @Column(nullable = false,name="transaction_app_id")
    private List<TransactionApp> transactionUsers;



}
