package com.projet_6.pay_my_buddy.JB.DAL.repository;

import com.projet_6.pay_my_buddy.JB.model.entity.TransactionApp;
import com.projet_6.pay_my_buddy.JB.model.entity.TransactionBank;
import com.projet_6.pay_my_buddy.JB.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionAppRepository extends CrudRepository<TransactionApp, Long>, PagingAndSortingRepository<TransactionApp, Long> {

    public List<TransactionApp> findAllBySender(User senderUser);
}
