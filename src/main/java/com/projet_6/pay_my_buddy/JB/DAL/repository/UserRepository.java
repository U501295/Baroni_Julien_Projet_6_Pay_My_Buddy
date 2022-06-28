package com.projet_6.pay_my_buddy.JB.DAL.repository;

import com.projet_6.pay_my_buddy.JB.model.entity.Authority;
import com.projet_6.pay_my_buddy.JB.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUserId(Long id);

    public List<User> findAllByEmailIs(String email);

    public List<User> findAllByEmailIsNot(String email);









    /*@Query
            (value = "use paymybuddyp6; select user_ressource_id from assoc_users_users where user_live_id = :connectedUserId", nativeQuery = true)
    //public Iterable<Long> findAUserContacts(@Param("connectedUserId") Long id);
    public Iterable<Long> (@Param("connectedUserId") Long id);*/
}
