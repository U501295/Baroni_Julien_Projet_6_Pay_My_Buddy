package com.projet_6.pay_my_buddy.JB.controller.DAL;

import com.projet_6.pay_my_buddy.JB.config.security.SecurityUtils;
import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionsDTO;
import com.projet_6.pay_my_buddy.JB.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/PayMyBuddy/")
public class TransferController {

    @Autowired
    UserService userService;


    @GetMapping("transfer")
    public MyTransactionsDTO getTheUserTransactions(@RequestParam(value = "id", required = true) Long id) {
        log.info("request to getTheUserTransactions of={} ", SecurityUtils.getUserName());
        MyTransactionsDTO myTransactionsDTO = new MyTransactionsDTO();
        //myTransactionsDTO.setMyTransactions(userService.getTheUserTransactions);
        return myTransactionsDTO;
    }

}
