package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.DAL.service.TransactionAppService;
import com.projet_6.pay_my_buddy.JB.DAL.service.UserService;
import com.projet_6.pay_my_buddy.JB.model.entity.TransactionApp;
import com.projet_6.pay_my_buddy.JB.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionAppServiceTest {

    @Autowired
    TransactionAppService transactionAppService;

    @Autowired
    UserService userService;


    @Test
    void addATransaction() {
        User user1 = userService.getUserByEmail("spring@user1.fr").get();
        User user2 = userService.getUserByEmail("spring@user2.fr").get();
        TransactionApp tran = new TransactionApp(user1, user2, 99.99f, "Test in prod");
        transactionAppService.addATransaction(tran);
        String endpoint = "";
    }


}