package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.model.entity.TransactionBank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionBankServiceTest {

    @Autowired
    TransactionBankService transactionBankService;

    @Test
    void getBankTransactionsFromAUserEmail() {
        List<TransactionBank> transactions = transactionBankService.getBankTransactionsFromAUserEmail("spring@user1.fr");
        String endpoint = "";
    }
}