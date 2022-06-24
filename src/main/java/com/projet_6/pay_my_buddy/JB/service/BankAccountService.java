package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.model.entity.BankAccount;
import com.projet_6.pay_my_buddy.JB.repository.BankAccountRepository;
import com.projet_6.pay_my_buddy.JB.repository.TransactionBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private UserService userService;

    public BankAccount getConnectedUserBankAccount(String email) {
        return bankAccountRepository.findBankAccountByUserId(userService.getUserByEmail(email).get());
    }

    public BankAccount getBankAccountFromAccountNumber(Long id) {
        return bankAccountRepository.findBankAccountByBankAccountId(id);
    }

    public BankAccount addBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }


}
