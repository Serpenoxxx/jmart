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
    @JsonAutowired(filepath=".scr/main/java/com/product.json", value= Product.class)
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
    public List<Product> getProductFiltered(@RequestParam(defaultValue="1") int page,
                                            @RequestParam(defaultValue="5") int pageSize,
                                            @RequestParam int accountId,
                                            @RequestParam String search,
                                            @RequestParam int minPrice,
                                            @RequestParam int maxPrice,
                                            @RequestParam ProductCategory category,
                                            @RequestParam boolean conditionUsed) {
        List<Product> filtered = new ArrayList<>();

        for (Product product : getJsonTable()) {

            if(product.accountId == accountId){
//                System.out.println(product);

                if(product.conditionUsed == conditionUsed){
                    if(product.category == category){
//                    System.out.println(product);
                        if((product.name.toUpperCase()).contains(search.toUpperCase())){

                            if(maxPrice == 0.0 && minPrice != 0.0){
                                if(product.price >= minPrice){
                                    System.out.println(product);
                                    filtered.add(product);
                                }
                            } else if(product.price >= minPrice && product.price <= maxPrice){
                                System.out.println(product);
                                filtered.add(product);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(filtered);
        return filtered;
    }
}
