package hu.fuz.account;

import hu.fuz.account.service.AccountService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AccountTest {

    @Test
    public void createAccount(){
        AccountService accountService = new AccountService();
        accountService.createAccount("Füzesi Zsolt");
        assertNotNull(accountService.getAccount("Füzesi Zsolt"));
        assertEquals(accountService.getAccount("Füzesi Zsolt").getAmount(),0);
    }

    @Test
    public void handleAccountBalance_increaseAmountTest(){
        AccountService accountService = new AccountService();
        accountService.createAccount("Zsolt");

        accountService.modifyBalance("Zsolt",100);

        assertEquals(accountService.getAccount("Zsolt").getAmount(),100);
    }

    @Test
    public void handleAccountBalance_decreaseAmountTest(){
        AccountService accountService = new AccountService();
        accountService.createAccount("Zsolt");

        accountService.modifyBalance("Zsolt",100);
        accountService.modifyBalance("Zsolt",-10);

        assertEquals(accountService.getAccount("Zsolt").getAmount(),90);
    }


    @Test
    public void handleAccountBalance_multipleAccountTest(){
        AccountService accountService = new AccountService();
        accountService.createAccount("Zsolt");
        accountService.createAccount("Macska");

        accountService.modifyBalance("Zsolt",100);
        accountService.modifyBalance("Macska",500);

        accountService.modifyBalance("Zsolt",-10);
        accountService.modifyBalance("Macska",-50);

        accountService.modifyBalance("Zsolt",-1);
        accountService.modifyBalance("Macska",5);

        assertEquals(accountService.getAccount("Zsolt").getAmount(),89);
        assertEquals(accountService.getAccount("Macska").getAmount(),455);
    }

}
