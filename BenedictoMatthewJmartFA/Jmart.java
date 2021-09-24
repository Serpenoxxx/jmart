package BenedictoMatthewJmartFA;


/**
 * Write a description of class Jmart here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Jmart
{
    // instance variables - replace the example below with your own
    public static void main(String[] args) {
        ShipmentDuration sd = createShipmentDuration();
        System.out.println(sd.isDuration(ShipmentDuration.KARGO));
    }
    
    //public static Product createProduct() {
       // PriceTag pt = new PriceTag(100000, 10);
       // return new Product("Piring Cantik", 5, false, pt, ProductCategory.KITCHEN);
    //}
    
    public static Coupon createCoupon() {
        return new Coupon("Pusp", 78, Type.REBATE, 200000, 10000);
    }
    
    public static ShipmentDuration createShipmentDuration() {
        return new ShipmentDuration(ShipmentDuration.KARGO);
    }  }