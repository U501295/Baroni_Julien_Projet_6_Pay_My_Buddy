package com.projet_6.pay_my_buddy.JB.DAL.service;

import com.projet_6.pay_my_buddy.JB.model.entity.BankAccount;
import com.projet_6.pay_my_buddy.JB.DAL.repository.BankAccountRepository;
import com.projet_6.pay_my_buddy.JB.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    @Autowired
    UserService userService;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount addBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount getBankAccountByUserMail(String email) {
        return bankAccountRepository.findBankAccountByUserId(userService.getUserByEmail(email).get());
    }


}
