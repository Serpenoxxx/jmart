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

    public static long DELIVERED_LIMIT_MS = 3;
        public static long ON_DELIVERY_LIMIT_MS = 4;
    public static long ON_PROGRESS_LIMIT_MS = 6;
    public static long WAITING_CONF_LIMIT_MS = 7;

    public static void main(String[] args) {
        /*
        try {
            JsonTable<Payment> table = new JsonTable<>(Payment.class, "randomPaymentList.json");
            ObjectPoolThread<Payment> paymentPool = new ObjectPoolThread<Payment>("Thread-PP", Jmart::paymentTimekeeper);
            paymentPool.start();
            table.forEach(payment -> paymentPool.add(payment));
            while (paymentPool.size() != 0);
            paymentPool.exit();
            while (paymentPool.isAlive());
            System.out.println("Thread exited successfully");
            Gson gson = new Gson();
            table.forEach(payment -> {
                //String history = gson.toJson(payment.history);
                //System.out.println(history);
            });
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }


        public static boolean paymentTimekeeper(Payment payment) {
            long time_elapsed = 0;

            for (Record record : payment.history) {
                if (record.status == Status.WAITING_CONFIRMATION && time_elapsed > WAITING_CONF_LIMIT_MS) {
                    record.status = Invoice.Status.FAILED;
                } else if (record.status == Status.ON_PROGRESS && time_elapsed > ON_PROGRESS_LIMIT_MS) {
                    record.status = Invoice.Status.FAILED;
                } else if (record.status == Status.ON_DELIVERY && time_elapsed > ON_DELIVERY_LIMIT_MS) {
                    record.status = Invoice.Status.DELIVERED;
                } else if (record.status == Status.DELIVERED && time_elapsed > DELIVERED_LIMIT_MS) {
                    record.status = Invoice.Status.FINISHED;
                }
            }
            return false;
        }

        System.out.println("account id:" + new Account(null, null, null, -1).id);
        System.out.println("account id:" + new Account(null, null, null, -1).id);
        System.out.println("account id:" + new Account(null, null, null, -1).id);

        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);


        try{
            List<Product> list = read("src/BenedictoMatthewJmartFA/randomProductList.json");
            List<Product> filter = filterByName(list, "sam", 0, 5);
            filter.forEach(product -> System.out.println(product.name));

        }
        catch(Throwable t){
            t.printStackTrace();
        }*/

    }




    /*

    public static List<Product> filterByAccountId(List<Product> list, int accountId, int page, int pageSize){
        List<Product> filter = new ArrayList<Product>();
        for (Product i : list) {
            if (i.accountId == accountId) {
                filter.add(i);
            }
        }

        return paginate(filter, page, pageSize, (e) -> e.accountId == accountId);
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
        return paginate(filter, page, pageSize, (e) -> e.name == e.name);
    }

    public static List<Product> filterByPrice(List<Product> list, double minPrice, double maxPrice){
    List<Product> result = new ArrayList<Product>();

    for(Product product : list){
        if(maxPrice == 0.0 && minPrice != 0.0){
            if(product.price >= minPrice){
                result.add(product);
            }
        } else if(product.price >= minPrice && product.price <= maxPrice){
            result.add(product);
        }

    }

        return result;
    }

    private static List<Product> paginate(List<Product> list, int page, int pageSize, Predicate<Product> pred) {
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException("Invalid Input!");
        }

        List<Product> paginated = new ArrayList<>();

        for (Product product : list) {
            if (pred.predicate(product)) {
                paginated.add(product);
            }
        }

        int index = (page) * pageSize;
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


     */}