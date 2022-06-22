package com.projet_6.pay_my_buddy.JB.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "transactions_bank")
public class TransactionBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_bank_id")
    private Long transactionBankId;

    @Column(nullable = false, name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false, name = "amount_transfered")
    private float bankTransferedAmount;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;


    public TransactionBank() {
    }


    public TransactionBank(float bankTransferedAmount, BankAccount bankAccount) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date date = cal.getTime();
        this.date = date;
        this.bankTransferedAmount = bankTransferedAmount;
        this.bankAccount = bankAccount;
    }
}
