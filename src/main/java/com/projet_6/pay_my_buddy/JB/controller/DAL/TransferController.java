package com.projet_6.pay_my_buddy.JB.controller.DAL;

import com.projet_6.pay_my_buddy.JB.config.security.SecurityUtils;
import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionLineDTO;
import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionsDTO;
import com.projet_6.pay_my_buddy.JB.model.entity.TransactionApp;
import com.projet_6.pay_my_buddy.JB.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/PayMyBuddy/")
public class TransferController {

    @Autowired
    UserService userService;


    @GetMapping("transfer/")
    public MyTransactionsDTO getTheUserTransactions() {
        log.info("request to getTheUserTransactions of={} ", SecurityUtils.getUserName());
        MyTransactionsDTO myTransactionsDTO = new MyTransactionsDTO();
        myTransactionsDTO.setMyTransactions(userService.getTheConnectedUserTransactions(SecurityUtils.getUserName()));
        return myTransactionsDTO;
    }

    //TODO : post mapping
    /*@PostMapping("transfer/")
    public MyTransactionsDTO postATransaction(@RequestBody TransactionApp transactionApp) {
        log.info("request to postATransaction from ={} ", SecurityUtils.getUserName());

    }*/


}
