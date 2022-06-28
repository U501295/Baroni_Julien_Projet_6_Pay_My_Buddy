package com.projet_6.pay_my_buddy.JB.DAL.service;

import com.projet_6.pay_my_buddy.JB.model.entity.Authority;
import com.projet_6.pay_my_buddy.JB.DAL.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    public String getAuthorityFromAuthorityId(long id) {
        String authority = authorityRepository.findAuthorityByAuthorityId(id).get().getAuthority();
        return authority;
    }

    public Authority getAuthorityFromRole(String role) {
        return authorityRepository.findAuthorityByAuthority(role).get();
    }
}
