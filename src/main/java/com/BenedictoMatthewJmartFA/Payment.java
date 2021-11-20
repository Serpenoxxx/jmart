package com.BenedictoMatthewJmartFA;

import java.util.ArrayList;
import java.util.Date;

public class Payment extends Invoice
{
    public int productCount;
    public Shipment shipment;


    Payment( int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    public double getTotalPay(){
        return 0;
    }
    

    public boolean validate(){
        return false;
    }
    

    public Invoice perform(){
        return null;
    }

    @Override
    public double getTotalPay(Product product) {
        return 0;
    }

    static class record {
        public Status status;
        public Date date;
        public String message;
    }

}
