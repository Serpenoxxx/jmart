package BenedictoMatthewJmartFA;

public class Payment extends Invoice implements Transactor 
{
    public int productCount;
    public Shipment shipment;
    
    Payment(int id, int buyerId, int productId, int productCount, Shipment shipment){
        super(id, buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
        
    }
    
    public double getTotalPay(){
        return 0;
    }
    
    @Override
    public boolean validate(){
        return false;
    }
    
    @Override
    public Invoice perform(){
        return null;
    }

    
}
