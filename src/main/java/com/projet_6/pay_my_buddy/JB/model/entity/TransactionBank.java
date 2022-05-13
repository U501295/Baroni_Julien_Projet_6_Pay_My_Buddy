package com.projet_6.pay_my_buddy.JB.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="transaction_bank")
public class TransactionBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_bank_id")
    private Long id;

    @Column(nullable = false,name="date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false,name="transferedAmount")
    private float bankTransferedAmount;

    @ManyToOne
    @Column(nullable = false,name="bank_account_id")
    private BankAccount bankAccount;
}
