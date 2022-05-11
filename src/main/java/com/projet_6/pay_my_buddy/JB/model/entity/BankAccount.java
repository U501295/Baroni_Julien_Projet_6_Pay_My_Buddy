package com.projet_6.pay_my_buddy.JB.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false,name="userId")
    private User userId;

    @OneToMany
    private List<TransactionBank> bankTransactions;


}
