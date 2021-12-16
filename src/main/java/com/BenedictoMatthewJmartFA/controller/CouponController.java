package com.BenedictoMatthewJmartFA.controller;

import com.BenedictoMatthewJmartFA.Algorithm;
import com.BenedictoMatthewJmartFA.Coupon;
import com.BenedictoMatthewJmartFA.dbjson.JsonAutowired;
import com.BenedictoMatthewJmartFA.dbjson.JsonTable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


/**
 * Controls all requests related to coupon with RestController
 * implements BasicGetController
 *
 * @author Benedicto Matthew W
 */

@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon>{

    @JsonAutowired(filepath=".scr/main/java/com/coupon.json", value= Coupon.class)
    public static JsonTable<Coupon> couponTable;

    public JsonTable<Coupon> getJsonTable(){
        return couponTable;
    }

    /**
     * Checks if coupon is used.
     *
     * @param  id  checks the coupon id.
     * @return  boolean representing condition of coupon.
     */

    @GetMapping("/{id}/isUsed")
    public boolean isUsed(int id){
        for(Coupon coupon: getJsonTable()){
            if(coupon.id == id){
                return coupon.isUsed();
            }
        }
        return false;
    }

    /**
     * Checks if coupon can be applied.
     *
     * @param  id  checks the coupon id.
     * @param  price gets the starting price.
     * @param  discount gets the disount.
     * @return  new price if coupon can be applied, false if can not.
     */

    @GetMapping("/{id}/canApply")
    public boolean canApply(int id, double price, double discount){
        for(Coupon coupon: getJsonTable()){
            if(coupon.id == id){
                return coupon.canApply(price,discount);
            }
        }
        return false;
    }

    /**
     * Gets list of available coupons.
     *
     * @param  page represents current page showing coupons.
     * @param  pageSize represents maximum number of coupons shown in a single page.
     * @return  new price if coupon can be applied, false if can not.
     */

    @GetMapping("/getAvailable")
    public List<Coupon> getAvailable (int page, int pageSize ){
        return Algorithm.paginate(couponTable, page, pageSize, pred-> !pred.isUsed());
    }
}
