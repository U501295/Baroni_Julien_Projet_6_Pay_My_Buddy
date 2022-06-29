package com.projet_6.pay_my_buddy.JB.config.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * L'exception correspond à la réaction au cas de figure
 * où une transaction dépasse le montant autorisé. On se sert alors de celle-ci pour déclencher un rollback.
 * <p>
 */

@Slf4j
public class NegativeBalanceException extends RuntimeException {

    public NegativeBalanceException() {
        super("You cannot process this transfer because your balance is too low ");
        log.debug("Negative Balance exception trigerred");
    }
}
