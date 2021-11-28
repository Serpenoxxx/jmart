package com.BenedictoMatthewJmartFA.controller;
import com.BenedictoMatthewJmartFA.Account;
import com.BenedictoMatthewJmartFA.Store;
import com.BenedictoMatthewJmartFA.dbjson.JsonAutowired;
import com.BenedictoMatthewJmartFA.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
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
        public Account login( @RequestParam String email, @RequestParam String password){
            String generatedPassword = null;
            try
            {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                byte[] bytes = md.digest();

                StringBuilder sb = new StringBuilder();
                for ( int i = 0; i<bytes.length; i++)
                {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }

                generatedPassword = sb.toString();
            } catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }


            for(Account acc : getJsonTable()){
                if(acc.email.equals(email) && acc.password.equals((password))){
                    return acc;
                }
            }

            return null;
        }

        @PostMapping("/register")
        public Account register( @RequestParam String name,  @RequestParam String email,  @RequestParam String password){
            Account account = new Account(name, email, password, 0.0);
            String passwordToHashReg = password;
            String generatedPassword = null;

            try
            {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(passwordToHashReg.getBytes());
                byte[] bytes = md.digest();
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < bytes.length; i++)
                {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                password = sb.toString();
            } catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }

            if(name.isBlank() == false){
                if(REGEX_PATTERN_EMAIL.matcher(email).matches() &&  REGEX_PATTERN_PASSWORD.matcher(password).matches()){
                    for(Account acc : getJsonTable()){
                        if(acc.email.equals(email)){
                            return null;
                        }
                    }
                    accountTable.add(account);
                    return account;
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

