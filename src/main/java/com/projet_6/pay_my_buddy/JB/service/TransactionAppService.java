package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.repository.TransactionAppRepository;
import com.projet_6.pay_my_buddy.JB.repository.TransactionBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionAppService {

    @Autowired
    private TransactionAppRepository transactionAppRepository;


}
