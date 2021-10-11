package BenedictoMatthewJmartFA;


public class Product
{
    public int accountId;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag pricetag;
    public ProductCategory category;
    public ProductRating rating;
    public int storeId;
    public byte shipmentPlans;

    public Product(int accountId, int storeId, String name, int weight, boolean conditionUsed, PriceTag pricetag, ProductCategory category, byte shipmentPlans)
    {
        this.accountId = accountId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.pricetag = pricetag;
        this.category = category;
        this.rating = new ProductRating();
        this.storeId = storeId;
        this.shipmentPlans = shipmentPlans;
    }

    public boolean read(String content) {
        return false;
    }
    
    public String toString() {
        return "Name: " + this.name + "\n" + "Weight: " + this.weight + "\n" + "conditionUsed: " + this.conditionUsed + "\n" + "priceTag: " + this.pricetag.getAdjustedPrice() + "\n" + "category: " + this.category + "\n" + "";
    }
}
