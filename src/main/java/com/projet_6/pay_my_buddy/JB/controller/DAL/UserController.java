package com.projet_6.pay_my_buddy.JB.controller.DAL;

import com.projet_6.pay_my_buddy.JB.config.security.SecurityUtils;
import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionLineDTO;
import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionsDTO;
import com.projet_6.pay_my_buddy.JB.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/PayMyBuddy/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/HOME")
    public String home(Model model) {
        log.info("Home page request from user {}", SecurityUtils.getUserMail());
        model.addAttribute("userMail", SecurityUtils.getUserMail());
        return "/PayMyBuddy/home";
    }

    @GetMapping("/transferApp")
    public String transferApp(Model model) {
        log.info("transferApp page request from user {}", SecurityUtils.getUserMail());
        MyTransactionsDTO myTransactionsDTO = new MyTransactionsDTO();
        myTransactionsDTO.setMyTransactions(userService.getTheConnectedUserTransactions(SecurityUtils.getUserMail()));
        Iterable<MyTransactionLineDTO> listTransactions = userService.getTheConnectedUserTransactions(SecurityUtils.getUserMail());
        model.addAttribute("transactions", myTransactionsDTO);
        model.addAttribute("transactionLines", listTransactions);
        return "/PayMyBuddy/transferApp";
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        log.info("contacts page request from user {}", SecurityUtils.getUserMail());
        List<String> names = userService.getContactsNameFromAConnectedUserEmail(SecurityUtils.getUserMail());
        Iterable<String> listNames = userService.getContactsNameFromAConnectedUserEmail(SecurityUtils.getUserMail());
        model.addAttribute("names", names);
        model.addAttribute("contactsNames", listNames);
        return "/PayMyBuddy/contacts";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        log.info("Profile page request from user {}", SecurityUtils.getUserMail());
        model.addAttribute("userName", SecurityUtils.getUserMail());
        model.addAttribute("name", userService.getNamefromEmail(SecurityUtils.getUserMail()));
        model.addAttribute("balance", userService.getBalancefromEmail(SecurityUtils.getUserMail()));
        model.addAttribute("authority", userService.getAuthorityFromEmail(SecurityUtils.getUserMail()));
        return "/PayMyBuddy/profile";
    }


}
