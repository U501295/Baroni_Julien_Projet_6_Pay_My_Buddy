package com.projet_6.pay_my_buddy.JB.DAL.repository;

import com.projet_6.pay_my_buddy.JB.model.entity.BankAccount;
import com.projet_6.pay_my_buddy.JB.model.entity.TransactionBank;
import com.projet_6.pay_my_buddy.JB.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionBankRepository extends CrudRepository<TransactionBank, Long>, PagingAndSortingRepository<TransactionBank, Long> {

    List<TransactionBank> findAllByBankAccount(BankAccount bankAccount);

}
