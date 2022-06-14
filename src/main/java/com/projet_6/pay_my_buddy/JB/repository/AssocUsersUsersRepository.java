package com.projet_6.pay_my_buddy.JB.repository;

import com.projet_6.pay_my_buddy.JB.model.entity.User;
import com.projet_6.pay_my_buddy.JB.model.joinTables.AssocUsersUsers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssocUsersUsersRepository extends CrudRepository<AssocUsersUsers, Long> {

    public List<AssocUsersUsers> findAllByUserLiveId(User user);

}
