package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.model.entity.User;
import com.projet_6.pay_my_buddy.JB.model.joinTables.AssocUsersUsers;
import com.projet_6.pay_my_buddy.JB.repository.AssocUsersUsersRepository;
import com.projet_6.pay_my_buddy.JB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssocUsersUsersService {

    @Autowired
    AssocUsersUsersRepository assocUsersUsersRepository;

    @Autowired
    UserRepository userRepository;

    public Iterable<AssocUsersUsers> getAllAssociationsCorrespondingToLiveUser(Long id) {
        return assocUsersUsersRepository.findAllByUserLiveId(userRepository.findById(id).get());
    }

}
