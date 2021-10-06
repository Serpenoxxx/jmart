package BenedictoMatthewJmartFA;


public class Product extends Recognizable implements FileParser
{
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag pricetag;
    public ProductCategory category;
    public ProductRating rating;
    public int storeId;
    public Shipment.MultiDuration multiDuration;

    public Product(int id, int storeId, String name, int weight, boolean conditionUsed, PriceTag pricetag, ProductCategory category, Shipment.MultiDuration multiDuration)
    {
        super(id);
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.pricetag = pricetag;
        this.category = category;
        this.rating = new ProductRating();
        this.storeId = storeId;
        this.multiDuration = multiDuration;
    }
    
    @Override
    public boolean read(String content) {
        return false;
    }
    
    public String toString() {
        return "Name: " + this.name + "\n" + "Weight: " + this.weight + "\n" + "conditionUsed: " + this.conditionUsed + "\n" + "priceTag: " + this.pricetag.getAdjustedPrice() + "\n" + "category: " + this.category + "\n" + "";
    }
}
