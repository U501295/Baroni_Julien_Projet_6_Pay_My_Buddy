package com.projet_6.pay_my_buddy.JB.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name="BankAccount")
public class BankAccount {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false,name="userId")
    private User userId;

    @OneToMany
    private List<TransactionBank> bankTransactions;


}
