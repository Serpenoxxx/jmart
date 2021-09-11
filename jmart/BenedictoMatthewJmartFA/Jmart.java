package BenedictoMatthewJmartFA;


/**
 * Write a description of class Jmart here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Jmart
{
    public static void main(String[] args){
    }
    
    public int getPromo(){
        return 0;
    }
    
    public String getCustomer(){
        return "OOP";
    }
    
    public float getDiscountPercentage(int before, int after){
        float discountPercentage = 0;
        if (before < after){
            return discountPercentage;
        }
        
        return discountPercentage = 100 * (before - after) / before;
    }
    
    public int getDiscountedPrice(int price, float discountPercentage){
        int discountedPrice = 0;
        if (discountPercentage > 100.0f){
            return discountedPrice;
        }
        
        return discountedPrice = price * (100 - discountPercentage) / 100;
    }
    
    public int getOriginalPrice(int discountedPrice,float discountPercentage){
        int originalPrice = 0;
        return originalPrice = discountedPrice / (1 - discountPercentage / 100);
    }
    
    public float getCommisionMultiplier(){
        return 0.05f;
    }
    
    public int getAdjustedPrice(int price){
        int adjustedPrice = 0;
        return adjustedPrice = price * (105 / 100);
    }
    
    public int getAdminFee(int price){
        int adminFee = 0;
        int adjustedPrice = 0;
        adjustedPrice = price * (105 / 100);
        adminFee = adjustedPrice - price;
        return adminFee;
    }