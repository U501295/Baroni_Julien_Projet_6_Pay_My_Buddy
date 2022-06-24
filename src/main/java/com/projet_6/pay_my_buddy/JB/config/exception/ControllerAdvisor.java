package com.projet_6.pay_my_buddy.JB.config.exception;

import com.projet_6.pay_my_buddy.JB.config.security.SecurityUtils;
import com.projet_6.pay_my_buddy.JB.controller.DAL.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor {

    @Autowired
    UserController userController;

    @ExceptionHandler(NegativeBalanceException.class)
    //public ResponseEntity<Object> handleDataNotFoundException(
    public String handleDataNotFoundException(
            NegativeBalanceException ex, HttpServletRequest request) {

        log.info("Balance is too low for user {} to make the transfer", SecurityUtils.getUserMail());
        String context = request.getRequestURI();
        switch (context) {
            case "/PayMyBuddy/transferBank":
                return "redirect:/PayMyBuddy/error/transferBankBalanceTooLow";
            case "/PayMyBuddy/transferApp":
                return "redirect:/PayMyBuddy/error/transferAppBalanceTooLow";
            default:
                return "redirect:/PayMyBuddy/error/homeBalanceTooLow";
        }
    }


}
