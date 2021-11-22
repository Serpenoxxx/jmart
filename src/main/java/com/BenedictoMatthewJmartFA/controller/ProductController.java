package com.BenedictoMatthewJmartFA.controller;

import com.BenedictoMatthewJmartFA.*;
import com.BenedictoMatthewJmartFA.dbjson.JsonAutowired;
import com.BenedictoMatthewJmartFA.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.BenedictoMatthewJmartFA.controller.AccountController.accountTable;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product>{
    @JsonAutowired(filepath=".scr/main/java/com/test.json", value= Product.class)
    public static JsonTable<Product> productTable;


    public JsonTable<Product> getJsonTable() {
        return productTable;
    }


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
    @GetMapping("/getFiltered")
    public List<Product> getProductFiltered(@RequestParam(defaultValue="7") int page,
                                            @RequestParam(defaultValue="4") int pageSize,
                                            @RequestParam int accountId,
                                            @RequestParam String search,
                                            @RequestParam int minPrice,
                                            @RequestParam int maxPrice,
                                            @RequestParam ProductCategory category) {
        List<Product> filtered = new ArrayList<>();

        for (Product product : getJsonTable()) {
            if (product.accountId == accountId || Objects.equals(product.name, search) || product.category == category) {
                filtered.add(product);
            }
        }

        if (minPrice == 0.0) {
            filtered.addAll(Algorithm.<Product>collect(getJsonTable(), (e) -> e.price <= maxPrice));
        } else if (maxPrice == 0.0) {
            filtered.addAll(Algorithm.<Product>collect(getJsonTable(), (e) -> e.price >= minPrice));
        } else {
            filtered.addAll(Algorithm.<Product>collect(getJsonTable(), (e) -> e.price >= minPrice && e.price <= maxPrice));
        }

        return filtered;
    }

}
