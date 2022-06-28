package com.projet_6.pay_my_buddy.JB.DAL.service;

import com.projet_6.pay_my_buddy.JB.model.DTO.MyTransactionLineDTO;
import com.projet_6.pay_my_buddy.JB.model.entity.TransactionApp;
import com.projet_6.pay_my_buddy.JB.model.entity.User;
import com.projet_6.pay_my_buddy.JB.model.joinTables.AssocUsersUsers;
import com.projet_6.pay_my_buddy.JB.DAL.repository.TransactionAppRepository;
import com.projet_6.pay_my_buddy.JB.DAL.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    private List<User> findDifferenceInUsersLists(List<User> first, List<User> second) {
        List<User> diff = new ArrayList<>();
        /*for (User objFirst : first) {
            if (second.contains(objFirst)) {

            } else {
                diff.add(objFirst);
            }
        }*/


        for (User objFirst : first) {
            boolean isPresent = false;
            for (User objSecond : second) {
                String email1 = objFirst.getEmail();
                String email2 = objSecond.getEmail();
                if (email1.equals(email2)) {
                    isPresent = true;
                }
                //TODO : regarder contains
            }
            if (!isPresent) {
                diff.add(objFirst);
            }

        }
        return diff;
    }

    public boolean compareUsers(User first, User second) {
        if (first.equals(second))
            return true;
        else {
            return false;
        }
    }

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

    public void updateUserAppAccount(String mailUser, float amount) {
        User liveUser = getUserByEmail(mailUser).get();
        liveUser.setAmountAppAccount(liveUser.getAmountAppAccount() + amount);
        addUser(liveUser);
    }

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

    public List<User> getExistingUsersNotAddedAsContactByLiveUser(String email) {
        List<AssocUsersUsers> assocs = (List<AssocUsersUsers>) assocUsersUsersService.getAllAssociationsCorrespondingToLiveUser(getUserByEmail(email).get().getUserId());
        List<User> contactUsers = new ArrayList<>();
        for (AssocUsersUsers assoc : assocs) {
            contactUsers.add(assoc.getUserRessourceId());
        }
        List<User> existingUsers = userRepository.findAllByEmailIsNot(email);
        List<User> existingUsersNotAddedAsContactByLiveUser = findDifferenceInUsersLists(existingUsers, contactUsers);
        return existingUsersNotAddedAsContactByLiveUser;
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

    public List<User> findPaginatedUsers(int pageNo, int pageSize) {

        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<User> pagedResult = userRepository.findAll(paging);

        return pagedResult.toList();
    }

    /*public List<String> findPaginatedContactsName(int pageNo, int pageSize, String email) {

        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<String> pagedResult = (Page<String>) getContactsNameFromAConnectedUserEmail(email); //(paging);

        return pagedResult.toList();
    }*/

    public Page<String> findPaginatedString(String context, Pageable pageable, String email) {
        List<String> contactsInfo = new ArrayList<>();
        switch (context) {
            case "contactsNames":
                contactsInfo = getContactsNameFromAConnectedUserEmail(email);
                break;
            case "contactsEmails":
                contactsInfo = getContactsEmailFromAConnectedUserEmail(email);
                break;
        }
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<String> list;

        if (contactsInfo.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, contactsInfo.size());
            list = contactsInfo.subList(startItem, toIndex);
        }

        Page<String> stringPage
                = new PageImpl<String>(list, PageRequest.of(currentPage, pageSize), contactsInfo.size());

        return stringPage;
    }

    public Page<MyTransactionLineDTO> findPaginatedTransactions(Pageable pageable, String email) {
        List<MyTransactionLineDTO> contactsInfo = getTheConnectedUserTransactions(email);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<MyTransactionLineDTO> list;

        if (contactsInfo.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, contactsInfo.size());
            list = contactsInfo.subList(startItem, toIndex);
        }

        Page<MyTransactionLineDTO> stringPage
                = new PageImpl<MyTransactionLineDTO>(list, PageRequest.of(currentPage, pageSize), contactsInfo.size());

        return stringPage;
    }


}
