package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionLineDTO;
import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionsDTO;
import com.projet_6.pay_my_buddy.JB.model.entity.TransactionApp;
import com.projet_6.pay_my_buddy.JB.model.entity.User;
import com.projet_6.pay_my_buddy.JB.repository.TransactionAppRepository;
import com.projet_6.pay_my_buddy.JB.repository.TransactionBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /*public MyTransactionLineDTO getTheUserAppTransactionLine(Long id) {
        User user = userService.getUserById(id).get();
        String userMail = userService.getUserName(user);
        //TODO : besoin de faire requete SQL

    }*/

}
