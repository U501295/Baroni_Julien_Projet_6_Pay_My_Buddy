package com.projet_6.pay_my_buddy.JB.service;

import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionLineDTO;
import com.projet_6.pay_my_buddy.JB.model.entity.TransactionApp;
import com.projet_6.pay_my_buddy.JB.model.entity.User;
import com.projet_6.pay_my_buddy.JB.model.joinTables.AssocUsersUsers;
import com.projet_6.pay_my_buddy.JB.repository.TransactionAppRepository;
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

    public Optional<User> getUserByEmail(String email) {
        Optional<User> connectedUser = userRepository.findUserByEmail(email);
        return connectedUser;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void addContact(String liveMail, String contactMail) {
        User liveUser = getUserByEmail(liveMail).get();
        List<User> contacts = liveUser.getContacts();
        contacts.add(getUserByEmail(contactMail).get());
        liveUser.setContacts(contacts);
        userRepository.save(liveUser);
    }

    public String getAuthorityFromEmail(String email) {
        String authority = getUserByEmail(email).get().getRole().getAuthority();
        return authority;
    }

    public float getBalanceFromEmail(String email) {
        float balance = getUserByEmail(email).get().getAmountAppAccount();
        return balance;
    }

    public List<Long> getContactsIdFromAConnectedUser(Long connectedUserId) {
        Iterable<AssocUsersUsers> associations = assocUsersUsersService.getAllAssociationsCorrespondingToLiveUser(getUserById(connectedUserId).get().getUserId());
        List<Long> contactsIds = new ArrayList<>();
        for (AssocUsersUsers u : associations) {
            contactsIds.add(u.getUserRessourceId().getUserId());
        }
        return contactsIds;
    }

    public List<String> getContactsNameFromAConnectedUserEmail(String email) {
        Iterable<AssocUsersUsers> associations = assocUsersUsersService.getAllAssociationsCorrespondingToLiveUser(getUserByEmail(email).get().getUserId());
        List<String> contactsName = new ArrayList<>();
        for (AssocUsersUsers u : associations) {
            contactsName.add(getNamefromEmail(u.getUserRessourceId().getEmail()));
        }
        return contactsName;
    }

    public List<String> getContactsEmailFromAConnectedUserEmail(String email) {
        Iterable<AssocUsersUsers> associations = assocUsersUsersService.getAllAssociationsCorrespondingToLiveUser(getUserByEmail(email).get().getUserId());
        List<String> contactsName = new ArrayList<>();
        for (AssocUsersUsers u : associations) {
            contactsName.add(u.getUserRessourceId().getEmail());
        }
        return contactsName;
    }

    public String getNamefromId(Long id) {
        String userName = getUserById(id).get().getFirstName() + "  " + getUserById(id).get().getLastName();
        return userName;
    }

    public String getNamefromEmail(String email) {
        String name = getUserByEmail(email).get().getFirstName() + "  " + getUserByEmail(email).get().getLastName();
        return name;
    }

    public float getBalancefromEmail(String email) {
        float balance = getUserByEmail(email).get().getAmountAppAccount();
        return balance;
    }


    public List<String> getContactsNamesFromTheirIds(List<Long> contactIds) {
        List<String> contactsNames = new ArrayList<>();
        for (Long l : contactIds) {
            contactsNames.add(getUserById(l).get().getFirstName() + "  " + getUserById(l).get().getLastName());
        }
        return contactsNames;
    }

    public List<TransactionApp> getTransactionsFromAConnectedUserId(Long connectedUserId) {
        //List <Long> contactsId = userService.getContactsIdFromAConnectedUser(connectedUserId);
        List<TransactionApp> transactionsFromAConnectedUser = transactionAppRepository.findAllBySender(getUserById(connectedUserId).get());
        /*for (Long contactId:contactsId) {
            transactionsFromAConnectedUser.add(transactionAppRepository.findAllByReceiver(userService.getUserById(contactId).get()))
        }
        transactionAppRepository.findAllByReceiver(userService.getUserById());*/
        return transactionsFromAConnectedUser;
    }

    public List<TransactionApp> getTransactionsFromAConnectedUserEmail(String email) {
        //List <Long> contactsId = userService.getContactsIdFromAConnectedUser(connectedUserId);
        List<TransactionApp> transactionsFromAConnectedUser = transactionAppRepository.findAllBySender(getUserByEmail(email).get());
        /*for (Long contactId:contactsId) {
            transactionsFromAConnectedUser.add(transactionAppRepository.findAllByReceiver(userService.getUserById(contactId).get()))
        }
        transactionAppRepository.findAllByReceiver(userService.getUserById());*/
        return transactionsFromAConnectedUser;
    }

    /*public List<Float> getAmountsFromAConnectedUserTransactions(String email) {
        //List <Long> contactsId = userService.getContactsIdFromAConnectedUser(connectedUserId);
        List<Float> AmountsFromAConnectedUserTransactions = transactionAppRepository.findAllBySender(getConnectedUserByEmail(email).get());
        /*for (Long contactId:contactsId) {
            transactionsFromAConnectedUser.add(transactionAppRepository.findAllByReceiver(userService.getUserById(contactId).get()))
        }
        transactionAppRepository.findAllByReceiver(userService.getUserById());
        return AmountsFromAConnectedUserTransactions;
    }*/

    public List<MyTransactionLineDTO> getTheConnectedUserTransactions(String email) {
        List<MyTransactionLineDTO> transactionsDto = new ArrayList<>();
        User connectedUser = getUserByEmail(email).get();
        List<TransactionApp> transactions = getTransactionsFromAConnectedUserId(connectedUser.getUserId());
        for (TransactionApp t : transactions) {
            MyTransactionLineDTO transactionDto = new MyTransactionLineDTO(getNamefromId(t.getReceiver().getUserId()), t.getDescription(), t.getAppTransferedAmount());
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
