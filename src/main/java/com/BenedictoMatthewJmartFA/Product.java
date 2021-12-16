package com.BenedictoMatthewJmartFA;

import com.BenedictoMatthewJmartFA.dbjson.Serializable;

/**
 * Contains the data for products.
 * Inherits Serializable.
 *
 * @author Benedicto Matthew W
 */

public class Product extends Serializable
{
    public int accountId;
    public String name;
    public int weight;
    public double discount;
    public boolean conditionUsed;
    public Treasury pricetag;
    public ProductCategory category;
    public ProductRating rating;
    public int storeId;
    public byte shipmentPlans;
    public double price;

    public Product(int accountId, String name, int weight, boolean conditionUsed, double price, double discount, ProductCategory category, byte shipmentPlans){
        this.accountId = accountId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.shipmentPlans = shipmentPlans;
    }

    public boolean read(String content) {
        return false;
    }
    
    public String toString() {
        return "Name: " + this.name + "\n" + "Weight: " + this.weight + "\n" + "conditionUsed: " + this.conditionUsed + "\n" + "priceTag: " + Treasury.getAdjustedPrice(100000, 10) + "\n" + "category: " + this.category + "\n" + "";
    }
}
