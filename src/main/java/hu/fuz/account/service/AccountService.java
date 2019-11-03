package hu.fuz.account.service;

import hu.fuz.account.model.Account;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {

    private Map<String,Account> accounts = new HashMap<>();

    public void createAccount(String userName) {
        Account account = new Account(userName,0);
        accounts.put(userName,account);
    }

    public Account getAccount(String userName) {
        return accounts.get(userName);
    }

    public void modifyBalance(String userName, int amount) {
        if(accounts.get(userName) != null){
            Account account = accounts.get(userName);
            account.setAmount( account.getAmount() + amount);
        }
    }


}
