package com.BenedictoMatthewJmartFA;

import com.BenedictoMatthewJmartFA.dbjson.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains account related data.
 * Inherits Serializable.
 *
 * @author Benedicto Matthew W
 */

public class Account extends Serializable
{
   public String name;
   public String email;
   public String password;
   public double balance;
   public Store store;
   public static final String REGEX_EMAIL = "^[a-zA-Z0-9_&_*~]+(?:\\.[a-zA-Z0-9_&_*~]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9-]+)*$";
   public static final String REGEX_PASSWORD = "^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$";

   public Account(String name, String email, String password, double balance)
   {
       this.name = name;
       this.email = email;
       this.password = password;
       this.balance = balance;
   }

   public String toString()
   {
        return "Name: " + this.name + "\n" + "Email: " + this.email + "\n" + 
        "Password: " + this.password;
   }

    /**
     * Checks the corresponding email and password according to the regex.
     *
     * @return  boolean representing whether the checked variables match the regex.
     */

   public boolean Validate()
    {
        Pattern email = Pattern.compile(REGEX_EMAIL);
        Pattern pass = Pattern.compile(REGEX_PASSWORD);
        Matcher emailVal = email.matcher(this.email);
        Matcher passVal = pass.matcher(this.password);
        if(emailVal.find() && passVal.find())
        {
            return true;
        }
        return false;
    }
}