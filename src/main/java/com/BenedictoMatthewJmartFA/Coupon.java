package com.BenedictoMatthewJmartFA;

public class Coupon
{
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
    
    public boolean canApply(double price, double discount){
        if((Treasury.getAdjustedPrice(100000, 10) >= this.minimum) && (used == false)){
            return true;
        } else{
            return false;
        }
    }
    
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