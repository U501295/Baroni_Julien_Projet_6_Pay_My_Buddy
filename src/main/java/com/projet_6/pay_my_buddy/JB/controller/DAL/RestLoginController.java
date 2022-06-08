package com.projet_6.pay_my_buddy.JB.controller.DAL;

import javax.annotation.security.RolesAllowed;

import com.projet_6.pay_my_buddy.JB.config.security.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/PayMyBuddy/")
public class RestLoginController {
    /*
    @RolesAllowed("USER")
    @RequestMapping("/")
    public String getUser()
    {
        return "Welcome User";
    }*/


    @GetMapping("accueil")
    public String regularUserLogin() {
        log.info(" Rest connexion request from user {}", SecurityUtils.getUserMail());
        return "Welcome to payMyBuddyApp dear   " + SecurityUtils.getUserMail();
    }

    @RolesAllowed({"USER", "ADMIN"})
    @RequestMapping("HOME/admin")
    public String getAdmin() {
        log.info("connexion request from admin {}", SecurityUtils.getUserMail());
        return "Welcome to payMyBuddyApp dear   " + SecurityUtils.getUserMail();
    }
}
