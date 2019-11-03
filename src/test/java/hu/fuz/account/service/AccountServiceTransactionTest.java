package hu.fuz.account.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountServiceTransactionTest {

    @Test
    public void createTransactionTest(){
        AccountService accountService = new AccountService();
        accountService.createAccount("Zsolt");
        accountService.createAccount("Zoli");

        accountService.modifyBalance("Zsolt",100_000);
        accountService.createTransaction("Zsolt","Zsoli",80_000);
    }

}