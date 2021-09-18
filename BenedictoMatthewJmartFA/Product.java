package BenedictoMatthewJmartFA;


public class Product
{
   private static int idCounter = 0;
   public int id;
   public String name;
   public int weight;
   public boolean conditionUsed;
   public PriceTag pricetag;
   public ProductCategory category;
   public ProductRating rating;
   
   public Product(String name, int weight, boolean conditionUsed, PriceTag pricetag, ProductCategory category)
   {
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.pricetag = pricetag;
        this.category = category;
        this.rating = new ProductRating();
        this.id = this.idCounter;
        this.idCounter++;
    }
}
