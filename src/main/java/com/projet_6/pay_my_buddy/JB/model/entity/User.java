package com.projet_6.pay_my_buddy.JB.model.entity;

import com.projet_6.pay_my_buddy.JB.model.joinTables.AssocUsersUsers;
import com.projet_6.pay_my_buddy.JB.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "pass_word")
    private String password;

    @Column(nullable = false, name = "amount_app_account")
    private float amountAppAccount;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "assoc_users_users",
            joinColumns = @JoinColumn(name = "user_live_id"),
            inverseJoinColumns = @JoinColumn(name = "user_ressource_id")
    )
    private List<User> contacts;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_account_id")
    private List<BankAccount> bankAccounts;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_app_id")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TransactionApp> transactionUsers;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_live_id")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<AssocUsersUsers> liveUsers;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_ressource_id")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<AssocUsersUsers> ressourceUsers;


}
