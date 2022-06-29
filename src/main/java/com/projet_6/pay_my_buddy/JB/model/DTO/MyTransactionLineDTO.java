package com.projet_6.pay_my_buddy.JB.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * DTO rassemblant le mapping des propriétés de nom du receveur, et les descriptions et montant d'une transaction.
 * Cela permet d'afficher plus facilement ces informations sur une seule ligne pour le front.
 * <p>
 */

@Getter
@Setter
public class MyTransactionLineDTO {
    private String name;
    private String description;
    private float amount;

    public MyTransactionLineDTO(String name, String description, float amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }
}
