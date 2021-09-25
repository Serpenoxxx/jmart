package BenedictoMatthewJmartFA;


public class Payment extends Transaction implements FileParser
{
    public int productId;
    public ShipmentDuration sd;
    
    public Payment(int id, int buyerId, Product product, ShipmentDuration shipmentDuraion){
        super(id, buyerId, product.id);
        //this.sd = shipmentDuration;
    }
    
    public Payment(int id, int buyerId, int storeId, int productId, ShipmentDuration shipmentDuration){
        super(id, buyerId, storeId);
        this.productId = productId;
        this.sd = shipmentDuration;
    }
    
    @Override
    public boolean read(String content){
        return false;
    }
    
    @Override
    public boolean validate(){
        return false;
    }
    
    @Override
    public Transaction perform(){
        return null;
    }
}
