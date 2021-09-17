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
    System.out.println();
    }
    
    public static int getPromo(){
        return 0;
    }
    
    public static String getCustomer(){
        return "OOP";
    }
    
    public static float getDiscountPercentage(int before, int after){
        float discountPercentage = 0;
        if (before < after){
            return discountPercentage;
        }
        else{
            discountPercentage = 100 * (before - after) / before;
        }
        return discountPercentage;
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage){
        int discountedPrice = 0;
        if (discountPercentage > 100.0f){
            return discountedPrice;
        }
        else{
            discountedPrice =  price * (int)(100 - discountPercentage) / 100;
        }
        return discountedPrice;
    }
    
    public static int getOriginalPrice(int discountedPrice,float discountPercentage){
        int originalPrice = 0;
        return originalPrice = discountedPrice / (1 - (int)discountPercentage / 100);
    }
    
    public static float getCommisionMultiplier(){
        return 0.05f;
    }
    
    public static int getAdjustedPrice(int price){
        return (int)((price * getCommisionMultiplier()) + price);
    }
    
    public static int getAdminFee(int price){
        return (int) (price * getCommisionMultiplier());
    }
}