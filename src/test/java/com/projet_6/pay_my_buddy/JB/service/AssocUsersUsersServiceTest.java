package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.DAL.service.AssocUsersUsersService;
import com.projet_6.pay_my_buddy.JB.DAL.service.UserService;
import com.projet_6.pay_my_buddy.JB.model.joinTables.AssocUsersUsers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssocUsersUsersServiceTest {

    @Autowired
    AssocUsersUsersService assocUsersUsersService;

    @Autowired
    UserService userService;


    @Test
    void getAllAssociationsCorrespondingToLiveUser() {
        Iterable<AssocUsersUsers> associationTest = assocUsersUsersService.getAllAssociationsCorrespondingToLiveUser(userService.getUserById(1L).get().getUserId());
        String endpoint = "";
    }
}