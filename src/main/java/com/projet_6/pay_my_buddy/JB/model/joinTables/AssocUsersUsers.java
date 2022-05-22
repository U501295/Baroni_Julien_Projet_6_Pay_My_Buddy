package com.projet_6.pay_my_buddy.JB.model.joinTables;

import com.projet_6.pay_my_buddy.JB.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "assoc_users_users")
public class AssocUsersUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "assoc_users_users_id")
    private Long assocUsersUsersId;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(nullable = false, name = "user_live_id", insertable = false, updatable = false)
    private User userLiveId;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(nullable = false, name = "user_ressource_id", insertable = false, updatable = false)
    private User userRessourceId;
}
