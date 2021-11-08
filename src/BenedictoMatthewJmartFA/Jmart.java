package BenedictoMatthewJmartFA;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import static BenedictoMatthewJmartFA.ProductCategory.PETCARE;


public class Jmart
{
    public static void main(String[] args) {
        /*
        System.out.println("account id:" + new Account(null, null, null, -1).id);
        System.out.println("account id:" + new Account(null, null, null, -1).id);
        System.out.println("account id:" + new Account(null, null, null, -1).id);

        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
        */

        try{
            List<Product> list = read("src/BenedictoMatthewJmartFA/randomProductList.json");
            List<Product> filter = filterByAccountId(list, 1, 0, 5);
            //list.forEach(product -> System.out.println(filter));
            System.out.println(filter);

        }
        catch(Throwable t){
            t.printStackTrace();
        }

    }

    public static List<Product> filterByAccountId(List<Product> list, int accountId, int page, int pageSize){
        List<Product> filter = new ArrayList<Product>();
        for (Product i : list) {
            if (i.accountId == accountId) {
                filter.add(i);
            }
        }

        return filter;
    }

    public static List<Product> filterByCategory(List<Product> list, ProductCategory category){
    List<Product> filter = Algorithm.<Product>collect(list, (e) -> e.category == category);
    return filter;
    }

    public static List<Product> filterByName(List<Product> list, String search, int page, int pageSize){
        List<Product> filter = new ArrayList<Product>();
        for (Product i : list) {
            if (i.name.toLowerCase().contains(search.toLowerCase())) {
                filter.add(i);
            }
        }
        return filter;
    }

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

    private static List<Product> paginate(List<Product> list, int page, int pageSize, Predicate<Product> pred) {
        if (page <= 0 || pageSize <= 0) {
            throw new IllegalArgumentException("Invalid Input!");
        }

        List<Product> paginated = new ArrayList<>();

        for (Product product : list) {
            if (pred.predicate(product) == true) {
                paginated.add(product);
            }
        }

        int index = (page - 1) * pageSize;
        if (paginated == null || paginated.size() <= index) {
            return Collections.emptyList();
        }
        int floorPage = Math.min(index + pageSize, paginated.size());
        return paginated.subList(index, floorPage);
    }

    public static List<Product> read(String filepath) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(filepath));
        Product[] products = new Gson().fromJson(reader, Product[].class);
        List<Product> result = new ArrayList<Product>();
        Collections.addAll(result, products);
        return result;

    }



}