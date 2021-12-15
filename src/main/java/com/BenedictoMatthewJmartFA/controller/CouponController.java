package com.BenedictoMatthewJmartFA.controller;

import com.BenedictoMatthewJmartFA.Algorithm;
import com.BenedictoMatthewJmartFA.Coupon;

import com.BenedictoMatthewJmartFA.dbjson.JsonAutowired;
import com.BenedictoMatthewJmartFA.dbjson.JsonTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon>{
    @JsonAutowired(filepath=".scr/main/java/com/coupon.json", value= Coupon.class)
    public static JsonTable<Coupon> couponTable;

    public JsonTable<Coupon> getJsonTable(){
        return couponTable;
    }

    @GetMapping("/{id}/isUsed")
    public boolean isUsed(int id){
        for(Coupon coupon: getJsonTable()){
            if(coupon.id == id){
                return coupon.isUsed();
            }
        }
        return false;
    }

    @GetMapping("/{id}/canApply")
    public boolean canApply(int id, double price, double discount){
        for(Coupon coupon: getJsonTable()){
            if(coupon.id == id){
                return coupon.canApply(price,discount);
            }
        }
        return false;
    }
    @GetMapping("/getAvailable")
    public List<Coupon> getAvailable (int page, int pageSize ){
        return Algorithm.paginate(couponTable, page, pageSize, pred-> !pred.isUsed());
    }
}
