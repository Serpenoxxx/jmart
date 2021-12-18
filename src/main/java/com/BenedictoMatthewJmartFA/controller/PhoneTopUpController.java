package com.BenedictoMatthewJmartFA.controller;

import com.BenedictoMatthewJmartFA.*;
import com.BenedictoMatthewJmartFA.dbjson.JsonAutowired;
import com.BenedictoMatthewJmartFA.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;
/**
 * Controls all requests related to phone top up with RestController
 * Implements BasicGetController
 *
 * @author Benedicto Matthew W
 */

@RestController
@RequestMapping("/phoneTopUp")
public class PhoneTopUpController implements BasicGetController<PhoneTopUp> {
    public static final String REGEX_PHONE = "^(\\+62|62|0)8[1-9][0-9]{6,9}$";
    public static final Pattern REGEX_PATTERN_PHONE = Pattern.compile(REGEX_PHONE);


    @JsonAutowired(value = PhoneTopUp.class, filepath = ".scr/main/java/com/phonetopUp.json")
    public static JsonTable<PhoneTopUp> phoneTopUpTable;

    public JsonTable<PhoneTopUp> getJsonTable(){
        return phoneTopUpTable;
    }

    /** Tops up buyer phone balance
     *
     * @param  buyerId represents buyer id
     * @param  productId represents product id
     * @param  phoneNumber represents phone number
     * @return newPhoneTopUp which includes updated phone balance
     */

    @PostMapping(value="/topUpbyPhone")
    public PhoneTopUp phoneTopUp(@RequestParam int buyerId,
                                 @RequestParam int productId,
                                 @RequestParam String phoneNumber
    ){
        PhoneTopUp newPhoneTopUp;
        Account account = Algorithm.<Account>find(AccountController.accountTable, e->e.id==buyerId);
        Product product = Algorithm.<Product>find(ProductController.productTable, e->e.id==productId);
        if(REGEX_PATTERN_PHONE.matcher(phoneNumber).matches()
                && account!=null
                && product!=null
                && account.balance>=product.price){
            newPhoneTopUp = new PhoneTopUp(buyerId,productId,phoneNumber, Invoice.Status.FINISHED);
            account.balance-=product.price;
        }else{
            newPhoneTopUp = new PhoneTopUp(buyerId,productId,phoneNumber, Invoice.Status.FAILED);
        }
        phoneTopUpTable.add(newPhoneTopUp);
        return newPhoneTopUp;
    }
}
