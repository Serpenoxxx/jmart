package com.BenedictoMatthewJmartFA;

import com.BenedictoMatthewJmartFA.dbjson.Serializable;

/**
 * Contains coupon related information and when it is used
 * during a transaction.
 *
 * @author Benedicto Matthew W
 */

public class Coupon extends Serializable {
    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private  boolean used;
    
    public Coupon(String name, int code, Type type, double cut, double minimum){
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        this.used = false;
    }
    
    public boolean isUsed(){
        return this.used;
    }

    /** Checks whether the coupon can be applied.
     * Coupon cannot be used if status is used.
     *
     * @param  price represents the item price
     * @param discount represents the discount
     * @return  boolean
     */

    public boolean canApply(double price, double discount){
        if((Treasury.getAdjustedPrice(100000, 10) >= this.minimum) && (!used)){
            return true;
        } else{
            return false;
        }
    }

    /** Applies the coupon to the product.
     * Will calculate the adjusted price
     *
     * @param  price represents the product price
     * @param  discount represents the discount applied to the product
     * @return  the final adjusted price
     */

    public double apply(double price, double discount){
         this.used = true;
        if(this.type == type.DISCOUNT){
                return (Treasury.getAdjustedPrice(100000, 10) * cut/100);
        } 
         return (Treasury.getAdjustedPrice(100000, 10) - this.cut);
    }
    
        public boolean read(String content){
    return false;
    }
}