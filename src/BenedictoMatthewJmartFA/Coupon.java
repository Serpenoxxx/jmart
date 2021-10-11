package BenedictoMatthewJmartFA;

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
    
    public boolean canApply(PriceTag priceTag){
        if((priceTag.getAdjustedPrice() >= this.minimum) && (used == false)){
            return true;
        } else{
            return false;
        }
    }
    
    public double apply(PriceTag priceTag){
         this.used = true;
        if(this.type == type.DISCOUNT){
                return (priceTag.getAdjustedPrice() * cut/100);
        } 
         return (priceTag.getAdjustedPrice() - this.cut);
    }
    
        public boolean read(String content){
    return false;
    }
}