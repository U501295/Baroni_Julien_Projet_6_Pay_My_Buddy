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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @GetMapping("/transferAppBalanceTooLow/page/{pageNumber}")
    public String getPaginatedTransaction(Model model, @PathVariable("pageNumber") int currentPage) {
        log.info("transferApp page request from user {}", SecurityUtils.getUserMail());
        List<String> contactEmails = userService.getContactsEmailFromAConnectedUserEmail(SecurityUtils.getUserMail());
        model.addAttribute("emails", contactEmails);
        MyTransactionsDTO myTransactionsDTO = new MyTransactionsDTO();
        myTransactionsDTO.setMyTransactions(userService.getTheConnectedUserTransactions(SecurityUtils.getUserMail()));
        Iterable<MyTransactionLineDTO> listTransactions = userService.getTheConnectedUserTransactions(SecurityUtils.getUserMail());
        model.addAttribute("transactions", myTransactionsDTO);
        model.addAttribute("transactionLines", listTransactions);
        model.addAttribute("connectedUser", userService.getUserByEmail(SecurityUtils.getUserMail()));


        model.addAttribute("contactsToBeAdded", userService.getExistingUsersNotAddedAsContactByLiveUser(SecurityUtils.getUserMail()));
        Page<MyTransactionLineDTO> pageOfTransactions = userService.findPaginatedTransactions(PageRequest.of(currentPage, 5), SecurityUtils.getUserMail());
        int totalTransactionPages = pageOfTransactions.getTotalPages();
        long totalTransactionItems = pageOfTransactions.getTotalElements();
        List<MyTransactionLineDTO> transactions = pageOfTransactions.getContent();

        model.addAttribute("currentPage", currentPage);

        model.addAttribute("totalTransactionPages", totalTransactionPages);
        model.addAttribute("totalTransactionItems", totalTransactionItems);
        model.addAttribute("paginatedTransactions", transactions);

        if (totalTransactionPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalTransactionPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "/PayMyBuddy/error/transferAppBalanceTooLow";
    }


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
        //return "/PayMyBuddy/error/transferAppBalanceTooLow";
        return getPaginatedTransaction(model, 0);
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
