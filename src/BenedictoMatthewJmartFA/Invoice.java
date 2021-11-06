package BenedictoMatthewJmartFA;

import java.util.Date;
import java.util.ArrayList;

public abstract class Invoice
{

    enum Status { WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED };
    enum Rating { NONE, BAD, NEUTRAL, GOOD };
    
    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating = Rating.NONE;
    public Status status = Status.WAITING_CONFIRMATION;
    public ArrayList<Record> history = new ArrayList<>();

    protected Invoice(int buyerId, int productId) {
        this.buyerId = buyerId;
        this.productId = productId;
        this.date = new Date();
        this.rating = Rating.NONE;
        this.status = Status.WAITING_CONFIRMATION;
    }
    
    public abstract double getTotalPay();


    
    class Record {
        public Status status;
        public Date date;
        public String message;
    }
}