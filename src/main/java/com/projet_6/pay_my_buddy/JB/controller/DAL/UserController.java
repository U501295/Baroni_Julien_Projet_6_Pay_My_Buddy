package com.projet_6.pay_my_buddy.JB.controller.DAL;

import com.projet_6.pay_my_buddy.JB.config.security.SecurityUtils;
import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionLineDTO;
import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionsDTO;
import com.projet_6.pay_my_buddy.JB.model.entity.TransactionApp;
import com.projet_6.pay_my_buddy.JB.model.entity.User;
import com.projet_6.pay_my_buddy.JB.service.TransactionAppService;
import com.projet_6.pay_my_buddy.JB.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/PayMyBuddy")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TransactionAppService transactionAppService;

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

    @PostMapping("/transferApp")
    public String addTransferApp(@RequestParam("email") String email, @RequestParam("amount") float amount, @RequestParam("description") String description, Model model) {
        List<String> contactEmails = userService.getContactsEmailFromAConnectedUserEmail(SecurityUtils.getUserMail());
        model.addAttribute("emails", contactEmails);
        TransactionApp transactionApp =
                new TransactionApp(userService.getUserByEmail(SecurityUtils.getUserMail()).get(),
                        userService.getUserByEmail(email).get(), amount, description);
        transactionAppService.addATransaction(transactionApp);
        return transferApp(model);
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        log.info("contacts page request from user {}", SecurityUtils.getUserMail());
        List<String> names = userService.getContactsNameFromAConnectedUserEmail(SecurityUtils.getUserMail());
        Iterable<String> listNames = userService.getContactsNameFromAConnectedUserEmail(SecurityUtils.getUserMail());
        List<String> emails = userService.getContactsEmailFromAConnectedUserEmail(SecurityUtils.getUserMail());
        List<User> contactsToBeAdded = userService.getExistingUsersNotAddedAsContactByLiveUser(SecurityUtils.getUserMail());
        model.addAttribute("names", names);
        model.addAttribute("contactsNames", listNames);
        model.addAttribute("emails", emails);
        model.addAttribute("contactsToBeAdded", contactsToBeAdded);
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

    @PostMapping("/contacts")
    public String addContact(@RequestParam("email") String email, Model model) {
        log.info("Post page request from user {}", SecurityUtils.getUserMail());
        //model.addAttribute("contactsToBeAdded",userService.getExistingUsersNotAddedAsContactByLiveUser(email))
        userService.addContact(SecurityUtils.getUserMail(), email);
        log.info("add contact request from user {}", SecurityUtils.getUserMail());
        return contacts(model);

    }


}
