package com.projet_6.pay_my_buddy.JB.repository;

import com.projet_6.pay_my_buddy.JB.model.entity.Authority;
import com.projet_6.pay_my_buddy.JB.model.entity.User;
import com.projet_6.pay_my_buddy.JB.model.joinTables.AssocUsersUsers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    Optional<Authority> findAuthorityByAuthorityId(long id);

    Optional<Authority> findAuthorityByAuthority(String role);

}
