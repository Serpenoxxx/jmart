package BenedictoMatthewJmartFA;

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

    
}
