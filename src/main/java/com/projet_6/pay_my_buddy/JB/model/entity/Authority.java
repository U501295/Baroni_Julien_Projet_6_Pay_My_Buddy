package com.projet_6.pay_my_buddy.JB.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Entité permettant d'associer un rôle à un User, ce rôle est utilisé à des fins de sécurité afin
 * de dimensionner les privilèges utilisateurs au type de profil connecté.
 * <p>
 */


@Entity
@Getter
@Setter
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long authorityId;

    @Column(nullable = false, name = "authority")
    private String authority;


    public Authority() {
        this.authority = "USER";
    }
}
