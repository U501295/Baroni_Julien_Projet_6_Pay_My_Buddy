package com.projet_6.pay_my_buddy.JB.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "transactions_app")

public class TransactionApp {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_app_id")
    private Long transactionAppId;

    @Column(nullable = false, name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(name = "user_sender_id")
    private User sender;

    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(name = "user_receiver_id")
    private User receiver;

    @Column(name = "transfered_amount")
    private float appTransferedAmount;

    @Column(name = "description_expense")
    private String description;


    public TransactionApp(User sender, User receiver, float appTransferedAmount, String description) {
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DATE, 1);

        Date date = cal.getTime();
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
        this.appTransferedAmount = appTransferedAmount;
        this.description = description;
    }

    public TransactionApp() {

    }
}
