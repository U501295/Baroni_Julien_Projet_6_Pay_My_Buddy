package com.projet_6.pay_my_buddy.JB.repository;

import com.projet_6.pay_my_buddy.JB.model.entity.BankAccount;
import com.projet_6.pay_my_buddy.JB.model.entity.TransactionBank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionBankRepository extends CrudRepository<TransactionBank, Long> {
}
