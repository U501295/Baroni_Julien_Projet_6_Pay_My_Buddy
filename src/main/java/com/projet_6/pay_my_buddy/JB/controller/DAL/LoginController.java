package com.projet_6.pay_my_buddy.JB.controller.DAL;

import javax.annotation.security.RolesAllowed;

import com.projet_6.pay_my_buddy.JB.config.security.SecurityUtils;
import com.projet_6.pay_my_buddy.JB.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/PayMyBuddy/")
public class LoginController {
    /*
    @RolesAllowed("USER")
    @RequestMapping("/")
    public String getUser()
    {
        return "Welcome User";
    }*/


    @GetMapping("HOME")
    public String regularUserLogin() {
        log.info("connexion request from user {}", SecurityUtils.getUserName());
        return "Welcome to payMyBuddyApp dear   " + SecurityUtils.getUserName();
    }

    @RolesAllowed({"USER", "ADMIN"})
    @RequestMapping("HOME/admin")
    public String getAdmin() {
        log.info("connexion request from admin {}", SecurityUtils.getUserName());
        return "Welcome to payMyBuddyApp dear   " + SecurityUtils.getUserName();
    }
}
