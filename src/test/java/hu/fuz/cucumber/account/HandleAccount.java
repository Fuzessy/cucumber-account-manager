package hu.fuz.cucumber.account;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.fuz.StartAccountManagerWeb;
import hu.fuz.account.service.AccountService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = StartAccountManagerWeb.class, loader = SpringBootContextLoader.class)
public class HandleAccount {

    private int PORT = 8080;
    private String SERVER_URL = "http://localhost:" + PORT;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AccountService accountService;

    @Before
    private void init(){
        this.SERVER_URL = "http://localhost:" + PORT;
    }

    @Given("{string}-nak van új számlája")
    public void createAccount(String userName) throws JSONException {
        System.out.println("username : " + userName);

        JSONObject requestJSON = new JSONObject();
        requestJSON.put("userName",userName);

        ResponseEntity response = postRequest(requestJSON, "/account",Void.class);

        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @When("{string} számlájához adunk {int} Ft-ot")
    public void modifyAccountBalance(String userName, int amount) throws JSONException {
        JSONObject request = new JSONObject();
        request.put("userName", userName);
        request.put("amount", amount);

        ResponseEntity response =
                postRequest(request,"/account/add",Void.class);

        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @Then("{string} számlaegyenlege {int} Ft lesz")
    public void checkAccountBalance(String userName, int amount) throws IOException {
        System.out.println("username : " + userName + " | amount : " + amount);
        assertEquals(amount, accountService.getAccount(userName).getAmount());
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity( headers);
//
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response =
//                restTemplate.exchange(
//                        SERVER_URL + "/account?{account-name}",
//                        HttpMethod.GET,
//                        entity,
//                        String.class,
//                        userName);
//
//        assertEquals(response.getStatusCode(),HttpStatus.OK);
//
//        JsonNode root = objectMapper.readTree(response.getBody());
//        assertEquals(root.get("userName").asText(),userName);
//        assertEquals(root.get("amount").asInt(),amount);
    }

    @Given("{string}-nak {int} Ft van a számláján")
    public void createAccountWithInitialBalance(String userName, int amount) throws JSONException {
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

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity response =
                    restTemplate.postForEntity(
                            SERVER_URL + "/transaction" ,
                            "{" +
                                    "  userFrom: '" + row.get("Kezdeményező") + "', " +
                                    "  userTo:   '" + row.get("Kedvezményezett") + "', " +
                                    "  amount:    " + row.get("Összeg") +
                                    "}",
                            Void.class);

            assertEquals(response.getStatusCode(),HttpStatus.OK);
        }
    }

    @Then("A felhasználók számlaegyenlege a következő lesz")
    public void createAccountAndSetBalance(DataTable dataTable) throws IOException {
        List<Map<String,String>> table = dataTable.asMaps();
        for(Map<String,String> row : table){
            Arrays.asList(
                    row.get("Felhasználó"),
                    row.get("Egyenleg")).forEach(a -> System.out.print(a + " | "));
            System.out.println("");

            checkAccountBalance(row.get("Felhasználó"), Integer.parseInt(row.get("Egyenleg")));
        }
    }

    private <T> ResponseEntity<T> postRequest(JSONObject json, String url, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<>(json.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                SERVER_URL + url,
                HttpMethod.POST,
                request,
                clazz);
    }

}
