package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionLineDTO;
import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionsDTO;
import com.projet_6.pay_my_buddy.JB.model.entity.TransactionApp;
import com.projet_6.pay_my_buddy.JB.model.entity.User;
import com.projet_6.pay_my_buddy.JB.repository.TransactionAppRepository;
import com.projet_6.pay_my_buddy.JB.repository.TransactionBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionAppService {

    @Autowired
    UserService userService;
    @Autowired
    private TransactionAppRepository transactionAppRepository;

    public Optional<TransactionApp> getTransactionAppById(Long id) {
        return transactionAppRepository.findById(id);
    }

    /*public MyTransactionLineDTO getTheUserAppTransactionLine(Long id) {
        User user = userService.getUserById(id).get();
        String userName = userService.getUserName(user);
        //TODO : besoin de faire requete SQL

    }*/

}
