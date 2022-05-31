package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionLineDTO;
import com.projet_6.pay_my_buddy.JB.model.entity.TransactionApp;
import com.projet_6.pay_my_buddy.JB.model.entity.User;
import com.projet_6.pay_my_buddy.JB.model.joinTables.AssocUsersUsers;
import com.projet_6.pay_my_buddy.JB.repository.AssocUsersUsersRepository;
import com.projet_6.pay_my_buddy.JB.repository.TransactionAppRepository;
import com.projet_6.pay_my_buddy.JB.repository.TransactionBankRepository;
import com.projet_6.pay_my_buddy.JB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssocUsersUsersService assocUsersUsersService;

    @Autowired
    private TransactionAppService transactionAppService;

    @Autowired
    private TransactionAppRepository transactionAppRepository;

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

    public List<Long> getContactsIdFromAConnectedUser(Long connectedUserId) {
        Iterable<AssocUsersUsers> associations = assocUsersUsersService.getAllAssociationsCorrespondingToLiveUser(getUserById(connectedUserId).get().getUserId());
        List<Long> contactsIds = new ArrayList<>();
        for (AssocUsersUsers u : associations) {
            contactsIds.add(u.getUserRessourceId().getUserId());
        }
        return contactsIds;
    }

    public String getAUserNamefromHisId(Long id) {
        String userName = getUserById(id).get().getFirstName() + "  " + getUserById(id).get().getLastName();
        return userName;
    }

    public List<String> getContactsNamesFromTheirIds(List<Long> contactIds) {
        List<String> contactsNames = new ArrayList<>();
        for (Long l : contactIds) {
            contactsNames.add(getUserById(l).get().getFirstName() + "  " + getUserById(l).get().getLastName());
        }
        return contactsNames;
    }

    public List<TransactionApp> getTransactionsFromAConnectedUser(Long connectedUserId) {
        //List <Long> contactsId = userService.getContactsIdFromAConnectedUser(connectedUserId);
        List<TransactionApp> transactionsFromAConnectedUser = transactionAppRepository.findAllBySender(getUserById(connectedUserId).get());
        /*for (Long contactId:contactsId) {
            transactionsFromAConnectedUser.add(transactionAppRepository.findAllByReceiver(userService.getUserById(contactId).get()))
        }
        transactionAppRepository.findAllByReceiver(userService.getUserById());*/
        return transactionsFromAConnectedUser;
    }

    public List<MyTransactionLineDTO> getTheConnectedUserTransactions(String email) {
        List<MyTransactionLineDTO> transactionsDto = new ArrayList<>();
        User connectedUser = getConnectedUserByEmail(email).get();
        List<TransactionApp> transactions = getTransactionsFromAConnectedUser(connectedUser.getUserId());
        for (TransactionApp t : transactions) {
            MyTransactionLineDTO transactionDto = new MyTransactionLineDTO(getAUserNamefromHisId(t.getReceiver().getUserId()), t.getDescription(), t.getAppTransferedAmount());
            transactionsDto.add(transactionDto);
        }
        return transactionsDto;
    }


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
