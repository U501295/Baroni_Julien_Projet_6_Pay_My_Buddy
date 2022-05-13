package com.projet_6.pay_my_buddy.JB.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_account_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false,name="user_id")
    @Column(name = "user_id")
    private User userId;

    @OneToMany
    @Column(name="transaction_bank_id")
    private List<TransactionBank> bankTransactions;


}
