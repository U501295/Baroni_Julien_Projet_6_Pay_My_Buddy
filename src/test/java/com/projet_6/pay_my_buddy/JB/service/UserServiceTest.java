package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.model.entity.User;
import com.projet_6.pay_my_buddy.JB.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        List<Long> receiversId = transactionAppService.getIdOfReceiversFromAConnectedUser(userService.getTransactionsFromAConnectedUser(1L));
        String endpoint = "";
    }

    @Test
    void getDescriptionsFromAConnectedUserTransactions() {
        List<String> descriptions = transactionAppService.getDescriptionsFromAConnectedUserTransactions(userService.getTransactionsFromAConnectedUser(1L));
        String endpoint = "";
    }

    @Test
    void getExpensesAmountFromAConnectedUser() {
        List<Float> expenses = transactionAppService.getExpensesAmountFromAConnectedUser(userService.getTransactionsFromAConnectedUser(1L));
        String endpoint = "";
    }

}