package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.model.entity.User;
import com.projet_6.pay_my_buddy.JB.repository.TransactionBankRepository;
import com.projet_6.pay_my_buddy.JB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void addContact(User user, Long idOfUserToAdd) {
        List<User> contacts = user.getContacts();
        contacts.add(getUserById(idOfUserToAdd).get());
        user.setContacts(contacts);

    }

    public void deleteContact(User user, Long idOfUserToDelete) {
        List<User> contacts = user.getContacts();
        contacts.remove(getUserById(idOfUserToDelete).get());
        user.setContacts(contacts);

    }


}
