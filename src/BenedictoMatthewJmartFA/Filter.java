package BenedictoMatthewJmartFA;
import java.util.ArrayList;


public class Filter
{
    public static ArrayList<PriceTag> filterPriceTag(PriceTag[] list, double value, boolean less){
        ArrayList<PriceTag> al = new ArrayList<PriceTag>();
        if (less){
            for(PriceTag pt : list){
                if(pt.getAdjustedPrice() < value){
                    al.add(pt);
                }
            }
        } else {
            for(PriceTag pt : list){
                if(pt.getAdjustedPrice() >= value){
                    al.add(pt);
                }
            }
        }
        return al;
    }
    
    public static void filterProductRating(ArrayList <ProductRating> list, double value, boolean less){
        if (less){
            for(ProductRating pr : list){
                if(pr.getAverage() < value){
                    list.remove(pr);
                }
            }
        } else {
            for(ProductRating pr : list){
                if(pr.getAverage() >= value){
                    list.remove(pr);
                }
            }
        }
    }
}
