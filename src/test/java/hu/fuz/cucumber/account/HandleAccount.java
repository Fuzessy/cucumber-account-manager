package hu.fuz.cucumber.account;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @Given("{string}-nak {int} Ft van a számláján")
    public void nak_Ft_van_a_számláján(String userName, int amount){
        System.out.println("username : " + userName + " | amount : " + amount);
    }

    @When("A következő tranzakciók jönnek létre")
    public void createTransactions(DataTable dataTable) {
        List<Map<String,String>> table = dataTable.asMaps();
        for(Map<String,String> row : table){
            Arrays.asList(
                    row.get("Kezdeményező"),
                    row.get("Kedvezményezett"),
                    row.get("Összeg")).forEach(a -> System.out.print(a + " | "));
            System.out.println("");
        }
    }

    @Then("A felhasználók számlaegyenlege a következő lesz")
    public void a_felhasználók_számlaegyenlege_a_következő_lesz(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new cucumber.api.PendingException();
    }


}
