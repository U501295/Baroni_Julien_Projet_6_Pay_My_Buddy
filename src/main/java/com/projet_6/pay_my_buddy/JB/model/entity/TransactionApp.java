package com.projet_6.pay_my_buddy.JB.model.entity;

import lombok.Getter;
import lombok.Setter;
import sun.util.calendar.BaseCalendar;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="transaction_app")
public class TransactionApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_app_id")
    private Integer id;

    @Column(nullable = false,name="date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @Column(name = "user_id")
    private User sender;

    @ManyToOne
    @Column(name = "user_id")
    private User receiver;

    @Column(name="transfered_amount")
    private float appTransferedAmount;

}
