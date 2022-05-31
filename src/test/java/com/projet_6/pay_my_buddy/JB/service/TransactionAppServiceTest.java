package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.model.entity.TransactionApp;
import com.projet_6.pay_my_buddy.JB.repository.TransactionAppRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionAppServiceTest {

    @Autowired
    TransactionAppService transactionAppService;


    @Test
    void getTransactionsFromAConnectedUser() {
    }


}