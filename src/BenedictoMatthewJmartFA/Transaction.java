package BenedictoMatthewJmartFA;

public abstract class Transaction extends Recognizable
{
    public String time;
    public int buyerId;
    public int storeId;
    public Rating rating;
    enum Rating{NONE, BAD, NEUTRAL, GOOD};

    protected Transaction(int id, int buyerId, int storeId){
        super(id);
        this.buyerId = buyerId;
        this.storeId = storeId;
        this.time = "TIME";
        this.rating = Rating.NONE;
    }

    protected Transaction(int id, Account buyer, Store store){
        super(id);
        this.buyerId = buyer.id;
        this.storeId = store.id;
        this.time = "TIME";
        this.rating = Rating.NONE;
    }
    
    public abstract boolean validate();
    
    public abstract Transaction perform();
}
