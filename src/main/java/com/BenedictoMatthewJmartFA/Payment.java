package com.BenedictoMatthewJmartFA;

import java.util.ArrayList;
import java.util.Date;

/**
 * Contains invoices related to payment information.
 *
 * @author Benedicto Matthew W
 */

public class Payment extends Invoice
{
    public ArrayList<Record> history = new ArrayList<>();
    public int productCount;
    public Shipment shipment;

    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId,productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    /** Gets the final payment amount
     *
     * @param  product represents product details
     * @return  final amount needed to be paid
     */

    @Override
    public double getTotalPay(Product product){
        return (product.price - ((product.price * product.discount)/100));
    }

    public static class Record{
        public final Date date;
        public String message;
        public Status status;

        public Record(Status status, String message){
            this.status = status;
            this.date = java.util.Calendar.getInstance().getTime();
            this.message = message;
        }
    }
}
