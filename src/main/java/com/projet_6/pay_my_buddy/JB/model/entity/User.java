package com.projet_6.pay_my_buddy.JB.model.entity;

import com.projet_6.pay_my_buddy.JB.config.exception.NegativeBalanceException;
import com.projet_6.pay_my_buddy.JB.model.joinTables.AssocUsersUsers;
import com.projet_6.pay_my_buddy.JB.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
@DynamicUpdate
public class User {

    //TODO : mettre des valeurs par defaut
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
    @org.hibernate.annotations.ColumnDefault("0")
    private float amountAppAccount;
    @Column(nullable = false, name = "enabled"/*, columnDefinition = "bigint not null default 1L "*/)
    @ColumnDefault("1L")
    private Long enabled;
    @ManyToMany(
            //fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST
                    //CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "assoc_users_users",
            joinColumns = @JoinColumn(name = "user_live_id"),
            inverseJoinColumns = @JoinColumn(name = "user_ressource_id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
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
    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(nullable = false, name = "authority_id")
    @ColumnDefault("USER")
    private Authority role;
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

    public User(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.amountAppAccount = 0;
    }


    public User() {

    }

    public void setAmountAppAccount(float amountAppAccount) {
        if (amountAppAccount < 0) {
            throw new NegativeBalanceException();
        } else {
            this.amountAppAccount = amountAppAccount;
        }
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof User)) {
            return false;
        }

        // typecast o to User so that we can compare data members
        User c = (User) o;

        // Compare the data members which are interesting for us and return accordingly
        if (this.email == c.email
                &&
                this.getFirstName() == c.getFirstName()
                &&
                this.getLastName() == c.getLastName()) {
            return true;
        } else {
            return false;
        }
    }


}
