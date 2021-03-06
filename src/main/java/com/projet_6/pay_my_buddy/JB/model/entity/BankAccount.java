package com.projet_6.pay_my_buddy.JB.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_account_id")
    private Long bankAccountId;

    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(nullable = false, name = "user_id")
    private User userId;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_account_id")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TransactionBank> bankTransactions;


    public BankAccount(User userId) {
        this.userId = userId;
    }

    public BankAccount() {

    }
}
