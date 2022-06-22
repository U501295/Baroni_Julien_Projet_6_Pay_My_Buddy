package com.projet_6.pay_my_buddy.JB.repository;

import com.projet_6.pay_my_buddy.JB.model.entity.BankAccount;
import com.projet_6.pay_my_buddy.JB.model.entity.TransactionBank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionBankRepository extends CrudRepository<TransactionBank, Long> {

    public List<TransactionBank> findAllByBankAccount(BankAccount bankAccount);

    public TransactionBank findByBankAccount(BankAccount bankAccount);
}
