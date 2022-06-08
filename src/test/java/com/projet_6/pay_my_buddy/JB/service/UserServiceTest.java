package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.model.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    TransactionAppService transactionAppService;

    @Test
    void getUserById() {
        Long id = 1L;
        Optional<User> userTest = userService.getUserById(id);
        Assertions.assertThat(userTest.isPresent()).isEqualTo(true);
        Assertions.assertThat(userTest.get().getEmail()).isEqualTo("spring@user.fr");

    }

    @Test
    void getUserByEmail() {
        String email = "spring@user1.fr";
        User userTest = userService.getConnectedUserByEmail(email).get();
        Assertions.assertThat(userTest.getUserId().equals(1L)).isTrue();
    }

    @Test
    void getUserContactsId() {
        Long id = 1L;
        List<Long> contactsIdTest = userService.getContactsIdFromAConnectedUser(id);
        String endpoint = "";
    }

    @Test
    void getUserContactsName() {
        List<Long> contactsIdTest = userService.getContactsIdFromAConnectedUser(1L);
        List<String> contactsNamesTest = userService.getContactsNamesFromTheirIds(contactsIdTest);
        String endpoint = "";
    }

    @Test
    void getIdOfReceiversFromAConnectedUser() {
        List<Long> receiversId = transactionAppService.getIdOfReceiversFromAConnectedUser(userService.getTransactionsFromAConnectedUserId(1L));
        String endpoint = "";
    }

    @Test
    void getDescriptionsFromAConnectedUserTransactions() {
        List<String> descriptions = transactionAppService.getDescriptionsFromAConnectedUserTransactions(userService.getTransactionsFromAConnectedUserId(1L));
        String endpoint = "";
    }

    @Test
    void getExpensesAmountFromAConnectedUser() {
        List<Float> expenses = transactionAppService.getExpensesAmountFromAConnectedUser(userService.getTransactionsFromAConnectedUserId(1L));
        String endpoint = "";
    }

    @Test
    void getNameFromEmail() {
        String name = userService.getNamefromEmail("spring@user1.fr");
        String endpoint = "";
    }

    @Test
    void getAuthorityFromEmail() {
        String authority = userService.getAuthorityFromEmail("spring@admin1.fr");
        String endpoint = "";
    }

    @Test
    void getBalanceFromEmail() {
        float balance = userService.getBalanceFromEmail("spring@admin1.fr");
        String endpoint = "";

    }

    @Test
    void getContactsEmailFromAConnectedUserEmail() {
        List<String> emails = userService.getContactsEmailFromAConnectedUserEmail("spring@user1.fr");
        String endpoint = "";
    }

    @Test
    void getContactsNameFromAConnectedUserEmail() {
        List<String> names = userService.getContactsNameFromAConnectedUserEmail("spring@user1.fr");
        String endpoint = "";
    }
}