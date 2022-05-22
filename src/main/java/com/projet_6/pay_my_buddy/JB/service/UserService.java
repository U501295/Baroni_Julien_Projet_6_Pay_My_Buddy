package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionLineDTO;
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

    public Optional<User> getConnectedUserByEmail(String email) {
        Optional<User> connectedUser = userRepository.findUserByEmail(email);
        return connectedUser;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void addContact(User user, Long idOfUserToAdd) {
        List<User> contacts = user.getContacts();
        contacts.add(getUserById(idOfUserToAdd).get());
        user.setContacts(contacts);

    }

    /*public Iterable<Long> getContactsId(Long id) {
        return userRepository.findAUserContacts(id);
    }*/

    public void deleteContact(User user, Long idOfUserToDelete) {
        List<User> contacts = user.getContacts();
        contacts.remove(getUserById(idOfUserToDelete).get());
        user.setContacts(contacts);

    }

    public String getUserName(User user) {
        String name = user.getFirstName() + "  " + user.getLastName();
        return name;
    }


}
