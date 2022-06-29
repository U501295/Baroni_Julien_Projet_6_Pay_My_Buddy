package com.projet_6.pay_my_buddy.JB.DAL.service;

import com.projet_6.pay_my_buddy.JB.model.entity.TransactionApp;
import com.projet_6.pay_my_buddy.JB.DAL.repository.TransactionAppRepository;
import com.projet_6.pay_my_buddy.JB.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionAppService {

    @Autowired
    AssocUsersUsersService assocUsersUsersService;
    @Autowired
    private TransactionAppRepository transactionAppRepository;


    public List<Long> getIdOfReceiversFromAConnectedUser(List<TransactionApp> transactionsFromAConnectedUser) {
        List<Long> receiversId = new ArrayList<>();
        for (TransactionApp t : transactionsFromAConnectedUser) {
            receiversId.add(t.getReceiver().getUserId());
        }
        return receiversId;
    }

    public List<String> getDescriptionsFromAConnectedUserTransactions(List<TransactionApp> transactionsFromAConnectedUser) {
        List<String> descriptions = new ArrayList<>();
        for (TransactionApp t : transactionsFromAConnectedUser) {
            descriptions.add(t.getDescription());
        }
        return descriptions;
    }

    public List<Float> getExpensesAmountFromAConnectedUser(List<TransactionApp> transactionsFromAConnectedUser) {
        List<Float> expenses = new ArrayList<>();
        for (TransactionApp t : transactionsFromAConnectedUser) {
            expenses.add(t.getAppTransferedAmount());
        }
        return expenses;
    }

    public TransactionApp addATransaction(TransactionApp transactionApp) {

        return transactionAppRepository.save(transactionApp);
    }

}
