package hu.fuz.cucumber.account;

import cucumber.api.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HandleAccount {

    @Given("{string}-nak van új számlája")
    public void createAccount(String userName){
        System.out.println("username : " + userName);
    }

    @When("{string} számlájához adunk {int} Ft-ot")
    public void modifyAccountBalance(String userName, int amount){
        System.out.println("username : " + userName +" | amount : " + amount);
    }
    @Then("{string} számlaegyenlege {int} Ft lesz")
    public void checkAccountBalance(String userName, int amount){
        System.out.println("username : " + userName + " | amount : " + amount);
    }
}
