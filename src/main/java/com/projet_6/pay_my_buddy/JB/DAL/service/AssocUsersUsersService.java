package com.projet_6.pay_my_buddy.JB.DAL.service;

import com.projet_6.pay_my_buddy.JB.model.joinTables.AssocUsersUsers;
import com.projet_6.pay_my_buddy.JB.DAL.repository.AssocUsersUsersRepository;
import com.projet_6.pay_my_buddy.JB.DAL.repository.UserRepository;
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
