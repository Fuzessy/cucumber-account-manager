package hu.fuz.account;

import hu.fuz.account.service.AccountService;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AccountTest {

    @Test
    public void createAccount(){
        AccountService accountService = new AccountService();
        accountService.createAccount("Füzesi Zsolt");
        assertNotNull(accountService.getAccount("Füzesi Zsolt"));
    }

    @Test
    public void handleAccountBalance(){
        AccountService accountService = new AccountService();
        accountService.createAccount("Zsolt");
        accountService.createAccount("Macska");

        accountService.modifyBalance("Zsolt",100);

    }

}
