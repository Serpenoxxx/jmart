package com.BenedictoMatthewJmartFA;

/**
 * Contains details regarding adjusted price of product.
 *
 * @author Benedicto Matthew W
 */

public class Treasury
{
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000.0;

    
    public static double getAdjustedPrice(double price, double discount){
        return (getDiscountedPrice(price, discount) + getAdminFee(price, discount));
    }

    /** Gets admin fee based on product, discount and commission multiplier
     *
     * @param  price represents the price of the product
     * @param  discount represents the discount applied to the product
     * @return  admin fee
     */

    public static double getAdminFee(double price, double discount){
        if (price < BOTTOM_PRICE) {
            return BOTTOM_FEE;
        }
        
        return (getDiscountedPrice(price, discount) * COMMISSION_MULTIPLIER);
    }

    /**Gets product's discounted price
     *
     * @param  price represents the price of the product
     * @param  discount represents the discount applied to the product
     * @return  discounted price
     */

    private static double getDiscountedPrice(double price, double discount){

        if (discount >= 100.0) {
            discount = 100;
            return 0.0;
        }
        
        return price - ((price * discount) / 100);
    }
}
