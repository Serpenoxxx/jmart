package com.BenedictoMatthewJmartFA;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains details regarding store owned by account.
 *
 * @author Benedicto Matthew W
 */

public class Store{
    public String name;
    public String address;
    public String phoneNumber;
    public double balance;
    public static final String REGEX_PHONE = "(^[0-9]{9,12}$)";
    public static final String REGEX_NAME = "(^[A-Z][a-z0-9][\\S\\s]{4,20}$)";

    
    public Store(String name, String address, String phoneNumber, double balance){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
    
    public boolean Read(String content){
        return false;
    }
    
    public String toString() {
        return "Name: " + this.name + "\n" + "address: " + this.address+ "\n" + "phoneNumber: " + this.phoneNumber;
    }
    
    public boolean Validate(){
        Pattern pattern = Pattern.compile(REGEX_NAME);
        Pattern pattern2 = Pattern.compile(REGEX_PHONE);
        Matcher matcher = pattern.matcher(this.name);
        Matcher matcher2 = pattern2.matcher(this.phoneNumber);

        if(matcher.find() && matcher2.find()){
            return true;
        }
        return false;
    }
}
