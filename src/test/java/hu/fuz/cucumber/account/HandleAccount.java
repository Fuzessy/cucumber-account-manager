package hu.fuz.cucumber.account;

import cucumber.api.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HandleAccount {

    @Given("Füzesi Zsolt-nak van számlája")
    public void createAccount(){

    }

    @When("Füzesi Zsolt számláhához adunk 100 Ft-ot")
    public void modifyAccountBalance(){

    }
    @Then("Füzesi Zsolt számlaegyenlege 100 Ft lesz")
    public void checkAccountBalance(){

    }
}
