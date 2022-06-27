package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionLineDTO;
import com.projet_6.pay_my_buddy.JB.model.entity.TransactionBank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

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

    @Test
    void findPaginatedBankTransfers() {
        List<TransactionBank> contact0 = transactionBankService.findPaginatedBankTransfers(PageRequest.of(0, 2), "spring@user1.fr").toList();
        List<TransactionBank> contact1 = transactionBankService.findPaginatedBankTransfers(PageRequest.of(1, 2), "spring@user1.fr").toList();
        List<TransactionBank> contact2 = transactionBankService.findPaginatedBankTransfers(PageRequest.of(2, 2), "spring@user1.fr").toList();

    }
}