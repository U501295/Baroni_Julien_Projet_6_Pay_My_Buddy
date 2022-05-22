package com.projet_6.pay_my_buddy.JB.model.entity;

import lombok.Getter;
import lombok.Setter;
import sun.util.calendar.BaseCalendar;

import javax.persistence.*;
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
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "user_sender_id")
    private User sender;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "user_receiver_id")
    private User receiver;

    @Column(name = "transfered_amount")
    private float appTransferedAmount;

    @Column(name = "description_expense")
    private String description;

}
