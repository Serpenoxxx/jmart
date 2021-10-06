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
         this.discount = 0.0;
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
        
        return (hargaDiskon * COMMISSION_MULTIPLIER);
    }
    
    private double getDiscountedPrice(){
        double discount = this.discount;
        if (this.discount > 100.0) {
            return 0.0;
        } else if (this.discount == 100.0) {
            return this.price;
        }
        
        return this.price - (this.price * (discount/100));
    }
}
