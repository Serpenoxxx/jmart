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
    public Shipment shipment;
    public int productCount;
    public ArrayList<Record> history;

    public Payment (int buyerId, int productId, int productCount, Shipment shipment)
    {
        super(buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    @Override
    public double getTotalPay(Product product)
    {
        return (product.price - (product.price * product.discount)/100);
    }

    public static class Record
    {
        public Status status;
        public final Date date;
        public String message;

        public Record (Status status, String message)
        {
            this.date = java.util.Calendar.getInstance().getTime();
            this.status = status;
            this.message = message;
        }
    }
}
