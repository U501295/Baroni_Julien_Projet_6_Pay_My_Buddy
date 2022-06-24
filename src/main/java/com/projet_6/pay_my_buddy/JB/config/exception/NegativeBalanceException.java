package com.projet_6.pay_my_buddy.JB.config.exception;

public class NegativeBalanceException extends RuntimeException {

    public NegativeBalanceException() {
        super("You cannot process this transfer because your balance is too low ");
    }
}
