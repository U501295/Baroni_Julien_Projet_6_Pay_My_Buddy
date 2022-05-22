package com.projet_6.pay_my_buddy.JB.model.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import java.util.List;


@Getter
@Setter
public class MyTransactionsDTO {
    List<MyTransactionLineDTO> myTransactions;
}
