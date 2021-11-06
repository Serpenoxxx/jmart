package BenedictoMatthewJmartFA;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonReader;


public class Jmart
{
    public static void main(String[] args) {
        System.out.println("account id:" + new Account(null, null, null, -1).id);
        System.out.println("account id:" + new Account(null, null, null, -1).id);
        System.out.println("account id:" + new Account(null, null, null, -1).id);
    }
/*
    public static List<Product> filterByPrice(List<Product> list, double minPrice, double macPrice){
    List<Product> result = new ArrayList<Product>();

    for(Product product : list){
        if(minPrice <= 0.0 && product.price < minPrice){
            continue;
        }
        if(minPrice <= 0.0 && product.price > minPrice){
            continue;
        }
        result.add(product);
    }
    return result;
    }

    public static List<Product> read(String filepath) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(filepath));
        Product[] products = newGson().fromJson(reader, Product[].class);
        List<Product> result = new ArrayList<Product>();
        Collections.addAll(result, products);
        return result;

    }

*/
}