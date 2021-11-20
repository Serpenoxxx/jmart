package com.BenedictoMatthewJmartFA.controller;

import com.BenedictoMatthewJmartFA.Account;
import com.BenedictoMatthewJmartFA.Payment;
import com.BenedictoMatthewJmartFA.dbjson.JsonAutowired;
import com.BenedictoMatthewJmartFA.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

public class PaymentController implements BasicGetController<Payment> {

    public static final long DELIVERED_LIMIT_MS = 3;
    public static final long ON_DELIVERY_LIMIT_MS = 4;
    public static final long ON_PROGRESS_LIMIT_MS = 6;
    public static final long WAITING_CONF_LIMIT_MS = 7;
    @JsonAutowired(filepath=".scr/main/java/com/test.json", value= Payment.class)
    public static JsonTable<Payment> paymentTable;

    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }
    @PostMapping("/{id}/accept")
    public boolean accept(int id){
    return true;
    }
    @PostMapping("/{id}/cancel")
    public boolean cancel(int id){
        return true;
    }
    @PostMapping("/create")
    public Payment create(int buyerId, int productId, int productCount, String shipmentAddress, byte shipmentPlan){
        return null;
    }

    @PostMapping("/{id}/submit")
    public boolean submit(int id, String receipt){
        return true;
    }

    private boolean timekeeper(Payment payment){
        return true;
    }
}
