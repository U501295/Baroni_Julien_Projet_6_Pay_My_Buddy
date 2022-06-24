package com.projet_6.pay_my_buddy.JB.controller.DAL;

import com.projet_6.pay_my_buddy.JB.config.security.SecurityUtils;
import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionLineDTO;
import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionsDTO;
import com.projet_6.pay_my_buddy.JB.model.entity.BankAccount;
import com.projet_6.pay_my_buddy.JB.model.entity.TransactionApp;
import com.projet_6.pay_my_buddy.JB.model.entity.TransactionBank;
import com.projet_6.pay_my_buddy.JB.model.entity.User;
import com.projet_6.pay_my_buddy.JB.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/PayMyBuddy/error")
public class ErrorController {

    @Autowired
    UserService userService;

    @Autowired
    TransactionAppService transactionAppService;

    @Autowired
    TransactionBankService transactionBankService;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityService authorityService;

    /*@RolesAllowed({"USER", "ADMIN"})
    @RequestMapping("HOME/admin")
    public String getAdmin() {
        log.info("connexion request from admin {}", SecurityUtils.getUserMail());
        return "Welcome to payMyBuddyApp dear   " + SecurityUtils.getUserMail();
    }*/


    @GetMapping("/transferAppBalanceTooLow")
    public String transferAppBalanceTooLow(Model model) {
        log.info("transferApp page request from user {}", SecurityUtils.getUserMail());
        List<String> contactEmails = userService.getContactsEmailFromAConnectedUserEmail(SecurityUtils.getUserMail());
        model.addAttribute("emails", contactEmails);
        MyTransactionsDTO myTransactionsDTO = new MyTransactionsDTO();
        myTransactionsDTO.setMyTransactions(userService.getTheConnectedUserTransactions(SecurityUtils.getUserMail()));
        Iterable<MyTransactionLineDTO> listTransactions = userService.getTheConnectedUserTransactions(SecurityUtils.getUserMail());
        model.addAttribute("transactions", myTransactionsDTO);
        model.addAttribute("transactionLines", listTransactions);
        model.addAttribute("connectedUser", userService.getUserByEmail(SecurityUtils.getUserMail()));
        return "/PayMyBuddy/error/transferAppBalanceTooLow";
    }

    @PostMapping("/transferAppBalanceTooLow")
    public String addTransferAppBalanceTooLow(@RequestParam("email") String email, @RequestParam("amount") float amount, @RequestParam("description") String description, Model model) {
        List<String> contactEmails = userService.getContactsEmailFromAConnectedUserEmail(SecurityUtils.getUserMail());
        model.addAttribute("emails", contactEmails);
        TransactionApp transactionApp =
                new TransactionApp(userService.getUserByEmail(SecurityUtils.getUserMail()).get(),
                        userService.getUserByEmail(email).get(), amount, description);
        userService.updateUserAppAccount(SecurityUtils.getUserMail(), -amount);
        userService.updateUserAppAccount(email, amount);
        transactionAppService.addATransaction(transactionApp);
        return transferAppBalanceTooLow(model);
    }

    @GetMapping("/transferBankBalanceTooLow")
    public String transferBankBalanceTooLow(Model model) {
        List<TransactionBank> bankTransactions = transactionBankService.getBankTransactionsFromAUserEmail(SecurityUtils.getUserMail());
        model.addAttribute("bankTransactions", bankTransactions);
        model.addAttribute("connectedUser", userService.getUserByEmail(SecurityUtils.getUserMail()));
        return "/PayMyBuddy/error/transferBankBalanceTooLow";
    }

    @PostMapping("/transferBankBalanceTooLow")
    public String addTransferBankBalanceTooLow(@RequestParam("bankAmount") float bankAmount, @RequestParam("bankAccount") BankAccount bankAccount, Model model) {
        log.info("POST transfer bank");
        TransactionBank transactionBank = new TransactionBank(bankAmount, bankAccount);
        transactionBankService.addABankTransaction(transactionBank);
        userService.updateUserAppAccount(SecurityUtils.getUserMail(), -bankAmount);
        return transferBankBalanceTooLow(model);
    }


}
