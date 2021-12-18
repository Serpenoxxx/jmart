package com.BenedictoMatthewJmartFA.controller;

import com.BenedictoMatthewJmartFA.Algorithm;
import com.BenedictoMatthewJmartFA.dbjson.JsonTable;
import com.BenedictoMatthewJmartFA.dbjson.Serializable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Gets the id or page, can be used universally.
 * T inherits Serializable.
 * @param  <T> a generic that can be used with all types of data.
 *
 * @author Benedicto Matthew W
 */

@RestController
public interface BasicGetController <T extends Serializable>{

    /** Gets an object from the Json Table matching the id
     *
     * @param  id represents the searched id
     * @return  the object matching the id
     */

    @GetMapping("/{id}")
    public default T getById(@PathVariable int id){
    for (T obj : getJsonTable()){
        if(obj.id == id){
            return obj;
        }
    }
    return getJsonTable().get(id);
    }

    public abstract JsonTable<T> getJsonTable();

    /** Gets the paginated page.
     *
     * @param  page represents the designated page
     * @param  pageSize represents the number of items inside the page
     * @return  paginated page by paginate algorithm
     */

    @GetMapping("/page")
    public default List<T> getPage(@RequestParam int page, @RequestParam int pageSize){
        page = 1;
        pageSize = 10;
    return Algorithm.paginate(getJsonTable(), page, pageSize, (e) -> true);
    }

}
