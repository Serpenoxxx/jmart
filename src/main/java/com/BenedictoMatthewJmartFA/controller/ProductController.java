package com.BenedictoMatthewJmartFA.controller;

import com.BenedictoMatthewJmartFA.*;
import com.BenedictoMatthewJmartFA.dbjson.JsonAutowired;
import com.BenedictoMatthewJmartFA.dbjson.JsonTable;

import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls all requests related to product with RestController
 * implements BasicGetController
 *
 * @author Benedicto Matthew W
 */

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product>{

    @JsonAutowired(filepath=".scr/main/java/com/product.json", value= Product.class)
    public static JsonTable<Product> productTable;

    public JsonTable<Product> getJsonTable() {
        return productTable;
    }

    /**
     * Creates a product according to the params.
     * Params will be passed from the request handled.
     *
     * @param  accountId represents the item made by the corresponding account.
     * @param  name represents the item's name.
     * @param  weight represents the item's weight.
     * @param  conditionUsed represents the item's condition.
     * @param  price represents the item's price.
     * @param  discount represents the item's discount
     * @param  category represents the item's category.
     * @param  shipmentPlans represents the item's shipment plans.
     * @return  new product.
     */

    @PostMapping("/create")
    public Product create(@RequestParam int accountId,
                          @RequestParam String name,
                          @RequestParam int weight,
                          @RequestParam boolean conditionUsed,
                          @RequestParam double price,
                          @RequestParam double discount,
                          @RequestParam ProductCategory category,
                          @RequestParam byte shipmentPlans
    ){
        JsonTable<Account> account = AccountController.accountTable;
        for (Account acc : account) {
            if ((acc.id == accountId) && (acc.store != null)) {
                Product newProduct = new Product(accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
                productTable.add(newProduct);
                return newProduct;
            }
        }
        return null;
    }

    @GetMapping("/{id}/store")
    public List<Product> getProductByStore(int id, int page, int pageSize){
        return Algorithm.paginate(productTable, page, pageSize, pred->pred.accountId == id);
    }

    /**
     * Gets the filtered products based on the params requested.
     * Will be passed from request.
     *
     * @param  page represents the current page number.
     * @param  pageSize represents the number of items to be shown in a page.
     * @param  accountId represents the current logged account.
     * @param  search represents the name of searched item.
     * @param minPrice represents the minimum price of searched item.
     * @param  maxPrice represents the maximum price of searched item.
     * @param  category represents the category of searched item.
     * @param  conditionUsed represents the condition of searched item.
     * @return  filtered products.
     */

    @GetMapping("/getFiltered")
    public List<Product> getProductFiltered(@RequestParam(defaultValue="1") int page,
                                            @RequestParam(defaultValue="5") int pageSize,
                                            @RequestParam int accountId,
                                            @RequestParam String search,
                                            @RequestParam int minPrice,
                                            @RequestParam int maxPrice,
                                            @RequestParam ProductCategory category,
                                            @RequestParam boolean conditionUsed) {
        List<Product> filtered = new ArrayList<>();

        for(Product item : productTable){
            if(item.name.contains(search) && item.price <= maxPrice && item.price >= minPrice && item.category == category && item.conditionUsed == conditionUsed){
                filtered.add(item);
            }
        }
        return Algorithm.<Product>paginate(filtered,page, pageSize, e -> {return true;});
    }
}
