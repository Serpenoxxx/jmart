package com.BenedictoMatthewJmartFA.controller;

import com.BenedictoMatthewJmartFA.Account;
import com.BenedictoMatthewJmartFA.Store;
import com.BenedictoMatthewJmartFA.dbjson.JsonAutowired;
import com.BenedictoMatthewJmartFA.dbjson.JsonTable;

import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * Controls all requests related to account with RestController
 * implements BasicGetController
 *
 * @author Benedicto Matthew W
 */

@RestController
    @RequestMapping("/account")
    public class AccountController implements BasicGetController<Account>
    {
        public static final String REGEX_EMAIL = "^[a-zA-Z0-9_&_*~]+(?:\\.[a-zA-Z0-9_&_*~]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9-]+)*$";
        public static final String REGEX_PASSWORD= "^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$";
        public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
        public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);

        @JsonAutowired(filepath=".scr/main/java/com/account.json", value=Account.class)
        public static JsonTable<Account> accountTable;

        @Override
        public JsonTable<Account> getJsonTable(){
        return accountTable;
        }

        /**
         * Validates the login credentials. Will check encrypted password.
         *
         * @param  email passed from request to validate email from json file.
         * @param  password passed from request to validate password from json file.
         * @exception  NoSuchAlgorithmException on error.
         * @return  Account class that stores all details regarding the logged account.
         */

        @PostMapping("/login")
        public Account login( @RequestParam String email,
                              @RequestParam String password){
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

        /**
         * Creates an account and stores it in the Json file.
         * Will only create if there are no blanks.
         *
         * @param  name passed from request to fill account name.
         * @param  email passed from request to fill account email.
         * @param  password passed from request to fill account password.
         * @exception  NoSuchAlgorithmException on error.
         * @return  account credentials of new account.
         */

        @PostMapping("/register")
        public Account register( @RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String password){
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

            if(!name.isBlank()){
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

        /**
         * Creates a store with data from the parameters.
         * Balance will always start at 0.
         *
         * @param  id to bind a store to a certain account id.
         * @param  name passed from request to fill store name.
         * @param  address passed from request to fill store address.
         * @param  phoneNumber passed from request to fill store phone number.
         * @return  store owned by the account.
         */

        @PostMapping("/{id}/registerStore")
        public Store registerStore(@PathVariable int id,
                                   @RequestParam String name,
                                   @RequestParam String address,
                                   @RequestParam String phoneNumber){
        for (Account account : accountTable){
            if(account.id == id && account.store == null){
                Store store = new Store(name, address, phoneNumber, 0.0);
                account.store = store;
                return store;
            }
        }
        return null;
        }

        /**
         * Tops up an account's balance.
         *
         * @param  id to check and bind a balance to a certain account id.
         * @param  balance passed from request to fill account balance.
         * @return  balance of the account.
         */

        @PostMapping("/{id}/topUp")
        public boolean topUp(@PathVariable int id,
                             @RequestParam double balance){
        for(Account account : accountTable){
            if(account.id == id){
            account.balance += balance;
            return true;
            }
        }
        return false;
        }

    }

