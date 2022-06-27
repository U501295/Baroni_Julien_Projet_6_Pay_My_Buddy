package com.projet_6.pay_my_buddy.JB.controller.DAL;

import com.projet_6.pay_my_buddy.JB.config.exception.NegativeBalanceException;
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
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
@RequestMapping("/PayMyBuddy")
public class UserController {

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

    @GetMapping("/contacts/page/{pageNumber}")
    public String getPaginatedString(Model model, @PathVariable("pageNumber") int currentPage) {
        model.addAttribute("contactsToBeAdded", userService.getExistingUsersNotAddedAsContactByLiveUser(SecurityUtils.getUserMail()));
        Page<String> pageOfNames = userService.findPaginatedString("contactsNames", PageRequest.of(currentPage, 2), SecurityUtils.getUserMail());
        Page<String> pageOfEmails = userService.findPaginatedString("contactsEmails", PageRequest.of(currentPage, 2), SecurityUtils.getUserMail());
        int totalNamePages = pageOfNames.getTotalPages();
        long totalNameItems = pageOfNames.getTotalElements();
        List<String> contactsName = pageOfNames.getContent();

        int totalEmailPages = pageOfEmails.getTotalPages();
        long totalEmailItems = pageOfEmails.getTotalElements();
        List<String> contactsEmail = pageOfEmails.getContent();

        model.addAttribute("currentPage", currentPage);

        model.addAttribute("totalNamePages", totalNamePages);
        model.addAttribute("totalNameItems", totalNameItems);
        model.addAttribute("contactsName", contactsName);

        model.addAttribute("totalEmailPages", totalEmailPages);
        model.addAttribute("totalEmailItems", totalEmailItems);
        model.addAttribute("contactsEmail", contactsEmail);

        if (totalNamePages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalNamePages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "/PayMyBuddy/contacts";
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
        //return "/PayMyBuddy/contacts";
        return getPaginatedString(model, 0);
    }

    @PostMapping("/contacts")
    public String addContact(@RequestParam("email") String email, Model model) {
        log.info("Post page request from user {}", SecurityUtils.getUserMail());
        model.addAttribute("contactsToBeAdded", userService.getExistingUsersNotAddedAsContactByLiveUser(email));
        userService.addContact(SecurityUtils.getUserMail(), email);
        log.info("add contact request from user {}", SecurityUtils.getUserMail());
        return contacts(model);

    }

    /*@GetMapping("/parameters/contacts")
    public String getAllPages(Model model) {
        log.info("contactsName page request from user {}", SecurityUtils.getUserMail());
        List<String> names = userService.getContactsNameFromAConnectedUserEmail(SecurityUtils.getUserMail());
        Iterable<String> listNames = userService.getContactsNameFromAConnectedUserEmail(SecurityUtils.getUserMail());
        List<String> emails = userService.getContactsEmailFromAConnectedUserEmail(SecurityUtils.getUserMail());
        List<User> contactsToBeAdded = userService.getExistingUsersNotAddedAsContactByLiveUser(SecurityUtils.getUserMail());
        model.addAttribute("names", names);
        model.addAttribute("contactsNames", listNames);
        model.addAttribute("emails", emails);
        model.addAttribute("contactsToBeAdded", contactsToBeAdded);
        Page<String> page = userService.findPaginatedString("contactsNames", PageRequest.of(0, 2), SecurityUtils.getUserMail());
        Page<String> pageOfEmails = userService.findPaginatedString("contactsEmails", PageRequest.of(0, 2), SecurityUtils.getUserMail());

        int totalNamePages = page.getTotalPages();
        long totalNameItems = page.getTotalElements();
        List<String> contactsName = page.getContent();

        int totalEmailPages = pageOfEmails.getTotalPages();
        long totalEmailItems = pageOfEmails.getTotalElements();
        List<String> contactsEmail = pageOfEmails.getContent();

        model.addAttribute("currentPage", 0);

        model.addAttribute("totalNamePages", totalNamePages);
        model.addAttribute("totalNameItems", totalNameItems);
        model.addAttribute("contactsName", contactsName);

        model.addAttribute("totalEmailPages", totalEmailPages);
        model.addAttribute("totalEmailItems", totalEmailItems);
        model.addAttribute("contactsEmail", contactsEmail);

        if (totalNamePages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalNamePages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return getPaginated(model, 0);
    }

    @GetMapping("/parameters/contacts/page/{pageNumber}")
    public String getPaginated(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<String> pageOfNames = userService.findPaginatedString("contactsNames", PageRequest.of(currentPage, 2), SecurityUtils.getUserMail());
        Page<String> pageOfEmails = userService.findPaginatedString("contactsEmails", PageRequest.of(currentPage, 2), SecurityUtils.getUserMail());
        int totalNamePages = pageOfNames.getTotalPages();
        long totalNameItems = pageOfNames.getTotalElements();
        List<String> contactsName = pageOfNames.getContent();

        int totalEmailPages = pageOfEmails.getTotalPages();
        long totalEmailItems = pageOfEmails.getTotalElements();
        List<String> contactsEmail = pageOfEmails.getContent();

        model.addAttribute("currentNamePage", currentPage);

        model.addAttribute("totalNamePages", totalNamePages);
        model.addAttribute("totalNameItems", totalNameItems);
        model.addAttribute("contactsName", contactsName);

        model.addAttribute("totalEmailPages", totalEmailPages);
        model.addAttribute("totalEmailItems", totalEmailItems);
        model.addAttribute("contactsEmail", contactsEmail);

        if (totalNamePages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, totalNamePages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "/PayMyBuddy/parameters/contacts";
    }*/


    @GetMapping("/registration")
    public String registration(Model model) {
        log.info("Registration page request");
        //mettre ici un log-off
        if (SecurityUtils.isUserConnected()) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "/PayMyBuddy/registration";
    }

    @PostMapping("/registration")
    public String addRegisteredUser(@RequestParam("email") String email, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("password") String password, Model model) {
        User userToRegister = new User(email, firstName, lastName, passwordEncoder.encode(password));
        userToRegister.setEnabled(1L);
        userToRegister.setRole(authorityService.getAuthorityFromRole("USER"));
        userService.addUser(userToRegister);
        BankAccount bankAccountOfUserToRegister = new BankAccount(userToRegister);
        bankAccountService.addBankAccount(bankAccountOfUserToRegister);
        return "redirect:/PayMyBuddy/HOME";
    }


    @GetMapping("/HOME")
    public String home(Model model) {
        log.info("Home page request from user {}", SecurityUtils.getUserMail());
        model.addAttribute("userMail", SecurityUtils.getUserMail());
        return "/PayMyBuddy/home";
    }

    @GetMapping("/transferApp/page/{pageNumber}")
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

        return "/PayMyBuddy/transferApp";
    }

    @GetMapping("/transferApp")
    public String transferApp(Model model) {
        log.info("transferApp page request from user {}", SecurityUtils.getUserMail());
        List<String> contactEmails = userService.getContactsEmailFromAConnectedUserEmail(SecurityUtils.getUserMail());
        model.addAttribute("emails", contactEmails);
        MyTransactionsDTO myTransactionsDTO = new MyTransactionsDTO();
        myTransactionsDTO.setMyTransactions(userService.getTheConnectedUserTransactions(SecurityUtils.getUserMail()));
        Iterable<MyTransactionLineDTO> listTransactions = userService.getTheConnectedUserTransactions(SecurityUtils.getUserMail());
        model.addAttribute("transactions", myTransactionsDTO);
        model.addAttribute("transactionLines", listTransactions);
        model.addAttribute("connectedUser", userService.getUserByEmail(SecurityUtils.getUserMail()));
        //return "/PayMyBuddy/transferApp";
        return getPaginatedTransaction(model, 0);
    }

    @PostMapping("/transferApp")
    public String addTransferApp(@RequestParam("email") String email, @RequestParam("amount") float amount, @RequestParam("description") String description, Model model) {

        List<String> contactEmails = userService.getContactsEmailFromAConnectedUserEmail(SecurityUtils.getUserMail());
        model.addAttribute("emails", contactEmails);
        TransactionApp transactionApp =
                new TransactionApp(userService.getUserByEmail(SecurityUtils.getUserMail()).get(),
                        userService.getUserByEmail(email).get(), amount, description);
        try {
            userService.updateUserAppAccount(SecurityUtils.getUserMail(), -amount);
            userService.updateUserAppAccount(email, amount);
        } catch (NegativeBalanceException e) {
            return "redirect:/PayMyBuddy/error/transferAppBalanceTooLow";
        }
        transactionAppService.addATransaction(transactionApp);
        return transferApp(model);
    }

    @GetMapping("/transferBank")
    public String transferBank(Model model) {
        List<TransactionBank> bankTransactions = transactionBankService.getBankTransactionsFromAUserEmail(SecurityUtils.getUserMail());
        model.addAttribute("bankTransactions", bankTransactions);
        model.addAttribute("connectedUser", userService.getUserByEmail(SecurityUtils.getUserMail()));
        return "/PayMyBuddy/transferBank";
    }

    @PostMapping("/transferBank")
    public String addTransferBank(@RequestParam("bankAmount") float bankAmount, @RequestParam("bankAccount") BankAccount bankAccount, Model model) {
        log.info("POST transfer bank");
        TransactionBank transactionBank = new TransactionBank(bankAmount, bankAccount);
        try {
            userService.updateUserAppAccount(SecurityUtils.getUserMail(), -bankAmount);
        } catch (NegativeBalanceException e) {
            return "redirect:/PayMyBuddy/error/transferBankBalanceTooLow";
        }
        transactionBankService.addABankTransaction(transactionBank);
        return transferBank(model);
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
