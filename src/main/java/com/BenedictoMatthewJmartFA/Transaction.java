package com.BenedictoMatthewJmartFA;

public abstract class Transaction
{
    public String time;
    public int buyerId;
    public int storeId;
    public Rating rating;
    enum Rating{NONE, BAD, NEUTRAL, GOOD};

    protected Transaction(int buyerId, int storeId){
        this.buyerId = buyerId;
        this.storeId = storeId;
        this.time = "TIME";
        this.rating = Rating.NONE;
    }

    protected Transaction(Account buyer, Store store){
        //this.buyerId = buyer.id;
        //this.storeId = store.id;
        this.time = "TIME";
        this.rating = Rating.NONE;
    }
    
    public abstract boolean validate();
    
    public abstract Transaction perform();
}
