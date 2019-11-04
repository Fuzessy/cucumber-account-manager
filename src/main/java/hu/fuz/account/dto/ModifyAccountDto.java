package hu.fuz.account.dto;

import lombok.Data;

@Data
public class ModifyAccountDto {
    private String userName;
    private int amount;
}
