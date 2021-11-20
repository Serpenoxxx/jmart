package com.BenedictoMatthewJmartFA.controller;

import com.BenedictoMatthewJmartFA.Algorithm;
import com.BenedictoMatthewJmartFA.dbjson.JsonTable;
import com.BenedictoMatthewJmartFA.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

public interface BasicGetController <T extends Serializable>{

    @GetMapping("/{id}")
    public default T getById(int id){
    for (T obj : getJsonTable()){
        if(obj.id == id){
            return obj;
        }
    }
    return null;
    }

    public abstract JsonTable<T> getJsonTable();

    @GetMapping("/page")
    public default List<T> getPage(int page, int pageSize){
        page = 1;
        pageSize = 2;
    return Algorithm.paginate(getJsonTable(), page, pageSize, (e) -> true);
    }


}
