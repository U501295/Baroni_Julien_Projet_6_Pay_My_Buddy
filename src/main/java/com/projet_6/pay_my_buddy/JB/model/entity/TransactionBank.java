package com.projet_6.pay_my_buddy.JB.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name="TransactionBank")
public class TransactionBank {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false,name="date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false,name="transferedAmount")
    private float bankTransferedAmount;

    @ManyToOne
    private BankAccount bankAccount;
}
