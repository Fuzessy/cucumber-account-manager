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
        accountService.createTransaction("Zsolt","Zoli",80_000);

        assertEquals(20_000,accountService.getAccount("Zsolt").getAmount());
        assertEquals(80_000,accountService.getAccount("Zoli").getAmount());
    }

    @Test
    public void createTransaction_multipleAccountsTest(){
        AccountService accountService = new AccountService();
        accountService.createAccount("Marci");
        accountService.createAccount("Macska");
        accountService.createAccount("Zoli");

        accountService.modifyBalance("Macska",100_000);
        accountService.createTransaction("Macska","Zoli",80_000);
        accountService.createTransaction("Zoli","Marci",60_000);
        accountService.createTransaction("Marci","Macska",40_000);

        assertEquals(20_000,accountService.getAccount("Zoli").getAmount());
        assertEquals(20_000,accountService.getAccount("Marci").getAmount());
        assertEquals(60_000,accountService.getAccount("Macska").getAmount());
    }

}