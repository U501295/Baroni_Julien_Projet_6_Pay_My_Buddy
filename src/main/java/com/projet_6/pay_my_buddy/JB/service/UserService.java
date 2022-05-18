package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.repository.TransactionBankRepository;
import com.projet_6.pay_my_buddy.JB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



}
