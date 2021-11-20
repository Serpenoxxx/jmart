package com.BenedictoMatthewJmartFA;


public class ProductRating
{
    private long total;
    private long count;
    
    public ProductRating(){
        this.total = 0;
        this.count = 0;
    }
    
    public void insert(int rating){
    count += 1;
    total = (long)(total + rating);
    }
    
    public double getAverage(){
        if(this.count == 0){
            return 0.0;
        }
        return this.total / this.count;
    }
    
    public long getCount(){
        return this.count;
    }
    
    public long getTotal(){
        return this.total;
    }
}
