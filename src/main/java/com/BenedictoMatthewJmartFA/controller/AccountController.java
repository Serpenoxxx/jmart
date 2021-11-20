package com.BenedictoMatthewJmartFA.controller;
import com.BenedictoMatthewJmartFA.Account;
import com.BenedictoMatthewJmartFA.Store;
import com.BenedictoMatthewJmartFA.dbjson.JsonAutowired;
import com.BenedictoMatthewJmartFA.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;


@RestController
    @RequestMapping("/account")
    public class AccountController implements BasicGetController<Account>
    {

        public static final String REGEX_EMAIL = "^[a-zA-Z0-9_&_*~]+(?:\\.[a-zA-Z0-9_&_*~]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9-]+)*$";
        public static final String REGEX_PASSWORD= "^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$";
        public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
        public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

        @JsonAutowired(filepath=".scr/main/java/com/test.json", value=Account.class)
        public static JsonTable<Account> accountTable;

        @Override
        public JsonTable<Account> getJsonTable(){
        return accountTable;
        }

        @PostMapping("/login")
        public Account login(String email, String password){
        for(Account account : getJsonTable()){
            if(account.email == email && account.password == password){
                return account;
            }
        }
        return null;
        }

        @PostMapping("/register")
        public Account register(String name, String email, String password){
        Account newAccount = new Account(name, email, password, 0.0);
        if ((!name.isBlank())){
            if(REGEX_PATTERN_EMAIL.matcher(email).matches() && REGEX_PATTERN_PASSWORD.matcher(password).matches()){
                for(Account account : getJsonTable()){
                    if(account.email.equals(email)){
                        return null;
                    }
                }
                accountTable.add(newAccount);
                return newAccount;
            }
        }
        return null;
        }
        @PostMapping("/{id}/registerStore")
        public Store registerStore(int id, String name, String address, String phoneNumber){
        for (Account account : accountTable){
            if(account.id == id && account.store == null){
                Store store = new Store(name, address, phoneNumber, 0.0);
                account.store = store;
                return store;
            }
        }
        return null;
        }
        @PostMapping("/{id}/topUp")
        public boolean topUp(int id, double balance){
        for(Account account : accountTable){
            if(account.id == id){
            account.balance += balance;
            return true;
            }
        }
        return false;
        }


    }

