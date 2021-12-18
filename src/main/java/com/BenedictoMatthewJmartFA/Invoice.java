package com.BenedictoMatthewJmartFA;

import com.BenedictoMatthewJmartFA.dbjson.Serializable;

import java.util.Date;

/**
 * Contains the invoice for payments made.
 *
 * @author Benedicto Matthew W
 */

public abstract class Invoice extends Serializable
{
    public int buyerId;
    public int complaintId;
    public Date date;
    public int productId;
    public Rating rating;

    protected Invoice(int buyerId, int productId){
        this.rating = Rating.NONE;
        this.date =java.util.Calendar.getInstance().getTime();
        this.complaintId = -1;
        this.buyerId = buyerId;
        this.productId = productId;
    }

    public abstract double getTotalPay(Product product);

    public enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED, DELIVERED
    }

    enum Rating{
        NONE, BAD, NEUTRAL, GOOD
    }


}