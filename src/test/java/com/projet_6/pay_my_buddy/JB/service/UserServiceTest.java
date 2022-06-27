package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionLineDTO;
import com.projet_6.pay_my_buddy.JB.model.entity.Authority;
import com.projet_6.pay_my_buddy.JB.model.entity.BankAccount;
import com.projet_6.pay_my_buddy.JB.model.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    TransactionAppService transactionAppService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    BankAccountService bankAccountService;

    @Test
    void getUserById() {
        Long id = 1L;
        Optional<User> userTest = userService.getUserById(id);
        Assertions.assertThat(userTest.isPresent()).isEqualTo(true);
        Assertions.assertThat(userTest.get().getEmail()).isEqualTo("spring@user.fr");

    }

    @Test
    void getUserByEmail() {
        String email = "spring@user14.fr";
        User userTest = userService.getUserByEmail(email).get();
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

    @Test
    void addContact() {
        userService.addContact("spring@user2.fr", "spring@user4.fr");
    }

    //email, first_name, last_name, pass_word, amount_app_account,enabled,authority_id
    @Test
    void addUser() {
        User user = new User("spring@user8.fr", "Eight", "Ocho", passwordEncoder.encode("user8"));
        user.setEnabled(1L);
        user.setRole(authorityService.getAuthorityFromRole("USER"));
        userService.addUser(user);
    }

    @Test
    void existingUsersNotAddedAsContactByLiveUser() {
        List<User> test = userService.getExistingUsersNotAddedAsContactByLiveUser("spring@user1.fr");
        String endpoint = "";
    }

    @Test
    void compareUsers() {
        User user = new User("spring@user7.fr", "TouKi", "TouKa", "password");
        user.setEnabled(1L);
        user.setRole(authorityService.getAuthorityFromRole("USER"));
        User user2 = new User("spring@user7.fr", "TouK", "TouKa", "password");
        user2.setEnabled(1L);
        user2.setRole(authorityService.getAuthorityFromRole("USER"));
        boolean test = userService.compareUsers(user, user2);
        String endpoint = "";

    }

    @Test
    void processTransactionBetweenUsers() {
        //userService.processTransactionBetweenUsers("spring@user1.fr", "spring@user2.fr", 50f);
        userService.updateUserAppAccount("spring@user1.fr", -50f);
    }

    @Test
    void addABankAccount() {
        BankAccount testBankAccount = new BankAccount(userService.getUserByEmail("spring@user1.fr").get());
        bankAccountService.addBankAccount(testBankAccount);
    }


    @Test
    void findPaginated() {
        List<User> user0 = userService.findPaginatedUsers(0, 2);
        List<User> user1 = userService.findPaginatedUsers(1, 2);
        List<User> user2 = userService.findPaginatedUsers(2, 2);
        List<User> user3 = userService.findPaginatedUsers(3, 2);
        List<User> user4 = userService.findPaginatedUsers(4, 2);
    }

    @Test
    void findPaginatedContactsName() {
        List<String> contact0 = userService.findPaginatedString("contactsEmail", PageRequest.of(0, 2), "spring@user1.fr").toList();
        List<String> contact1 = userService.findPaginatedString("contactsEmail", PageRequest.of(1, 2), "spring@user1.fr").toList();
        List<String> contact2 = userService.findPaginatedString("contactsEmail", PageRequest.of(2, 2), "spring@user1.fr").toList();


    }

    @Test
    void findPaginatedTransactions() {
        List<MyTransactionLineDTO> contact0 = userService.findPaginatedTransactions(PageRequest.of(0, 2), "spring@user1.fr").toList();
        List<MyTransactionLineDTO> contact1 = userService.findPaginatedTransactions(PageRequest.of(1, 2), "spring@user1.fr").toList();
        List<MyTransactionLineDTO> contact2 = userService.findPaginatedTransactions(PageRequest.of(2, 2), "spring@user1.fr").toList();
    }
}