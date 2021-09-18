package BenedictoMatthewJmartFA;


public class PriceTag
{
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000.0;
    public double discount;
    public double price;
    
    public PriceTag (double price){
         this.price = price;
    }
    
    public PriceTag (double price, double discount){
         this.price = price;
         this.discount = discount;
    }
    
    public double getAdjustedPrice(){
        return getDiscountedPrice() + getAdminFee();
    }
    
    public double getAdminFee(){
        double hargaDiskon = getDiscountedPrice();
        
        if (hargaDiskon < BOTTOM_FEE) {
            return BOTTOM_FEE;
        }
        
        return hargaDiskon - (hargaDiskon * (COMMISSION_MULTIPLIER / 100));
    }
    
    private double getDiscountedPrice(){
        if (this.discount >= 100){
            return 0.0;
        }
        
        return this.price - (this.price * (this.discount / 100));
    }
}
