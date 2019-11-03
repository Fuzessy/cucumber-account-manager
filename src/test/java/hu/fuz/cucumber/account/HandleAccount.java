package hu.fuz.cucumber.account;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HandleAccount {

    @LocalServerPort
    private int PORT;
    private String SERVER_URL = "http://localhost:" + PORT;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Given("{string}-nak van új számlája")
    public void createAccount(String userName){
        System.out.println("username : " + userName);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response =
            restTemplate.postForEntity(
                SERVER_URL + "/account",
                "{ username: '" + userName+ "' }",
                Void.class);

        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @When("{string} számlájához adunk {int} Ft-ot")
    public void modifyAccountBalance(String userName, int amount){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response =
                restTemplate.postForEntity(
                        SERVER_URL + "/account/add",
                        "{ " +
                                "  userName: '" + userName+ "' " +
                                "  amount: " +  amount +
                                "} ",
                        Void.class);

        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @Then("{string} számlaegyenlege {int} Ft lesz")
    public void checkAccountBalance(String userName, int amount) throws IOException {
        System.out.println("username : " + userName + " | amount : " + amount);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        SERVER_URL + "/account?{account-name}",
                        String.class,
                        userName);

        assertEquals(response.getStatusCode(),HttpStatus.OK);

        JsonNode root = objectMapper.readTree(response.getBody());
        assertEquals(root.get("userName").asText(),userName);
        assertEquals(root.get("amount").asInt(),amount);
    }

    @Given("{string}-nak {int} Ft van a számláján")
    public void createAccountWithInitialBalance(String userName, int amount){
        System.out.println("username : " + userName + " | amount : " + amount);

        createAccount(userName);
        modifyAccountBalance(userName,amount);
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
    public void createAccountAndSetBalance(DataTable dataTable) {
        List<Map<String,String>> table = dataTable.asMaps();
        for(Map<String,String> row : table){
            Arrays.asList(
                    row.get("Felhasználó"),
                    row.get("Egyenleg")).forEach(a -> System.out.print(a + " | "));
            System.out.println("");
        }
    }


}
