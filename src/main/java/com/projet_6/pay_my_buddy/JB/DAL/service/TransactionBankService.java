package com.projet_6.pay_my_buddy.JB.DAL.service;

import com.projet_6.pay_my_buddy.JB.model.entity.TransactionBank;
import com.projet_6.pay_my_buddy.JB.DAL.repository.BankAccountRepository;
import com.projet_6.pay_my_buddy.JB.DAL.repository.TransactionBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public Page<TransactionBank> findPaginatedBankTransfers(Pageable pageable, String email) {
        List<TransactionBank> contactsInfo = getBankTransactionsFromAUserEmail(email);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<TransactionBank> list;

        if (contactsInfo.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, contactsInfo.size());
            list = contactsInfo.subList(startItem, toIndex);
        }

        Page<TransactionBank> transactionBankPage
                = new PageImpl<TransactionBank>(list, PageRequest.of(currentPage, pageSize), contactsInfo.size());

        return transactionBankPage;
    }

}
