package hu.fuz.cucumber.account;

import cucumber.api.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HandleAccount {

    @Given("\\((.*)\\)-nak van új számlája")
    public void createAccount(String userName){
        System.out.println("username : " + userName);
    }

    @When("\\((.*)\\) számláhához adunk (\\d+) Ft-ot")
    public void modifyAccountBalance(String userName, long amount){
        System.out.println("username : " + userName +" | amount : " + amount);
    }
    @Then("\\((.*)\\) számlaegyenlege (\\d+) Ft lesz")
    public void checkAccountBalance(String userName, long amount){
        System.out.println("username : " + userName + " | amount : " + amount);
    }
}
