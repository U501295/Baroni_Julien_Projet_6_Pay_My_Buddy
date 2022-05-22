package com.projet_6.pay_my_buddy.JB.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
