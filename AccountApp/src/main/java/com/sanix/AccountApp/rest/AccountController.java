package com.sanix.AccountApp.rest;

import com.sanix.AccountApp.models.Account;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class AccountController {
    private static List<Account> accounts=
            Stream.of(new Account(1, "Saving", new BigDecimal(1000.00)),
                new Account(2, "current", new BigDecimal(5000.00)),
                new Account(3, "saving", new BigDecimal(3000.00))
            ).collect(Collectors.toList());


    @GetMapping("/accounts")
    public List<Account> getAllAccounts(){
        return accounts;
    }

    @GetMapping("/accounts/{accountId}")
    public Account getAccount(@PathVariable String accountId){
        Optional<Account> accountOptional=accounts.stream()
                                                    .filter(
                                                            account->Integer.parseInt(accountId)==account.getAccountId())
                                                    .findFirst();
        return accountOptional.isPresent() ? accountOptional.get():new Account(0, "default account", new BigDecimal(0.0));
    }

    @PostMapping("/accounts")
    public List<Account> addAccount(@RequestBody Account account){
        accounts.add(account);
        return accounts;
    }


    @DeleteMapping("/accounts/{accountId}")
    public List<Account> deleteAccount(@PathVariable String accountId){
        accounts.removeIf(account -> Integer.parseInt(accountId)==account.getAccountId());
        return accounts;
    }

    @PutMapping("/accounts")
    public List<Account> updateAccount(@RequestBody Account acc){

        accounts.removeIf(account -> acc.getAccountId()==account.getAccountId());
        accounts.add(acc);
        return accounts;
    }
}
