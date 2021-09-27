package BenedictoMatthewJmartFA;


public abstract class Invoice extends Recognizable implements FileParser
{
    public String date;
    public int buyerId;
    public int complaintId;
    public int productId;
    public Rating rating;
    public Status status;
    
    enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED
    }
    
    enum Rating{
        NONE, BAD, NEUTRAL, GOOD
    }
    
    protected Invoice(int id, int buyerId, int productId){
        super(id);
        this.rating = Rating.NONE;
        this.date = "Jan";  
        this.status = Status. WAITING_CONFIRMATION;
        this.buyerId = buyerId;
        this.productId = productId;
    }
    
    public abstract double getTotalPay();
    
    @Override
    public boolean read(String content) {
        return false;
    }
         
}
