package com.projet_6.pay_my_buddy.JB.config.exception;

import com.projet_6.pay_my_buddy.JB.config.security.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(NegativeBalanceException.class)
    //public ResponseEntity<Object> handleDataNotFoundException(
    public void NegativeBalanceException(
            NegativeBalanceException ex, HttpServletRequest request) {

        log.info("Balance is too low for user {} to make the transfer", SecurityUtils.getUserMail());

    }


}
