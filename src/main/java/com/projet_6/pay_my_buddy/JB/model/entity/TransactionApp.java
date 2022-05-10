package com.projet_6.pay_my_buddy.JB.model.entity;

import lombok.Getter;
import lombok.Setter;
import sun.util.calendar.BaseCalendar;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "TransactionApp")
@Getter
@Setter
public class TransactionApp {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false,name="date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    @Column(name="transferedAmount")
    private float appTransferedAmount;

}
