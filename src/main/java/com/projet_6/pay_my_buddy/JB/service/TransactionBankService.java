package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.model.entity.TransactionBank;
import com.projet_6.pay_my_buddy.JB.repository.BankAccountRepository;
import com.projet_6.pay_my_buddy.JB.repository.TransactionBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionBankService {

    @Autowired
    private TransactionBankRepository transactionBankRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private UserService userService;

    public List<TransactionBank> getBankTransactionsFromAUserEmail(String email) {
        return transactionBankRepository.findAllByBankAccount(bankAccountRepository.findBankAccountByUserId(userService.getUserByEmail(email).get()));
    }

    public TransactionBank addABankTransaction(TransactionBank transactionBank) {
        return transactionBankRepository.save(transactionBank);
    }

}
