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
    
    public static Product create(){
        return new Product("Ps5", 10, false, new PriceTag(10000, 10.0), ProductCategory.GAMING); 
    }
    
    //public static Coupon createCoupon(){
    //}
}