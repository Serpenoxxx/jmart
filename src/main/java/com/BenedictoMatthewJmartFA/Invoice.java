package com.BenedictoMatthewJmartFA;

import com.BenedictoMatthewJmartFA.dbjson.Serializable;

import java.util.Date;

public abstract class Invoice extends Serializable
{

    public enum Status { WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED, DELIVERED };
    enum Rating { NONE, BAD, NEUTRAL, GOOD };
    
    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId = -1;
    public Rating rating = Rating.NONE;
    public Status status = Status.WAITING_CONFIRMATION;


    protected Invoice(int buyerId, int productId) {
        super();
        this.buyerId = buyerId;
        this.productId = productId;
        this.date = java.util.Calendar.getInstance().getTime();
        this.rating = Rating.NONE;
        this.status = Status.WAITING_CONFIRMATION;
    }
    
    public abstract double getTotalPay(Product product);


    

}