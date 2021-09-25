package BenedictoMatthewJmartFA;


public class Product extends Recognizable
{
   private static int idCounter = 0;
   public int id;
   public String name;
   public int weight;
   public boolean conditionUsed;
   public PriceTag pricetag;
   public ProductCategory category;
   public ProductRating rating;
   public int storeId;
   public Product(int id,int storeId, String name, int weight, boolean conditionUsed, PriceTag pricetag, ProductCategory category){
        super(id);
        this.storeId = storeId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.pricetag = pricetag;
        this.category = category;
        this.rating = new ProductRating();
    }
    
    public Product(int id,Store store, String name, int weight, boolean conditionUsed, PriceTag pricetag, ProductCategory category){
        super(id);
        this.storeId = storeId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.pricetag = pricetag;
        this.category = category;
        this.rating = new ProductRating();
    }
}
