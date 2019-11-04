package hu.fuz.account.controller;


import hu.fuz.account.dto.ModifyAccountDto;
import hu.fuz.account.model.CreateAccountDto;
import hu.fuz.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("")
    public void createAccount(@RequestBody CreateAccountDto createAccountDto){
        accountService.createAccount(createAccountDto.getUserName());
    }

    @PostMapping("/add")
    public void modifyAccount(@RequestBody ModifyAccountDto modifyAccountDto){
        accountService.modifyBalance(modifyAccountDto.getUserName(),modifyAccountDto.getAmount());
    }
}
