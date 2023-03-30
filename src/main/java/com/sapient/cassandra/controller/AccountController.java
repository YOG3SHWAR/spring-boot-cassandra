package com.sapient.cassandra.controller;

import com.sapient.cassandra.domain.Account;
import com.sapient.cassandra.domain.AccountKey;
import com.sapient.cassandra.repository.AccountCrudRepository;
import com.sapient.cassandra.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountCrudRepository crudRepository;

    @GetMapping("/")
    String getStatus() {
        return "UP";
    }

    @PostConstruct
    public void createAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(Account.builder()
                .key(AccountKey.builder().accountNumber("1").sortCode("123").name("name1").build())
                .data("data1")
                .build());
        accounts.add(Account.builder()
                .key(AccountKey.builder().accountNumber("2").sortCode("123").name("name2").build())
                .data("data2")
                .build());
        accounts.add(Account.builder()
                .key(AccountKey.builder().accountNumber("1").sortCode("456").name("name3").build())
                .data("data3")
                .build());
        accounts.add(Account.builder()
                .key(AccountKey.builder().accountNumber("2").sortCode("456").name("name4").build())
                .data("data4")
                .build());
        repository.saveAll(accounts);
    }

    @GetMapping("/all-accounts")
    List<Account> getAllAccounts() {
        return repository.findAll();
    }

    @GetMapping("/get-account")
    Account getAccount() {
        return repository.findFirstByKeySortCodeAndKeyAccountNumber("456", "1");
    }

    @GetMapping("/get-accounts")
    Iterable<Account> getAccounts() {
        List<AccountKey> accountKeys = new ArrayList<>();
        accountKeys.add(AccountKey.builder()
                .sortCode("123")
                .accountNumber("1")
                .name("name1")
                .build());
        accountKeys.add(AccountKey.builder()
                .sortCode("456")
                .accountNumber("1")
//                .name("name3")
                .build());
//        return crudRepository.findAllById(accountKeys);
        return repository.findAllByKeySortCode(accountKeys);
    }

    @GetMapping("/get-accounts1")
    List<Account> getAccounts1() {
        List<String> sortCodes = new ArrayList<>();
        List<String> accountNos = new ArrayList<>();
        sortCodes.add("123");
        sortCodes.add("456");
        accountNos.add("1");
        sortCodes.add("1");
        return repository.findAllByKeySortCodeAndKeyAccountNumber(sortCodes, accountNos);
//        return repository.findAllByKeySortCode(sortCodes);
    }

}
