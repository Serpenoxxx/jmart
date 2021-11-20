package com.BenedictoMatthewJmartFA.controller;

import com.BenedictoMatthewJmartFA.Account;
import com.BenedictoMatthewJmartFA.Invoice;
import com.BenedictoMatthewJmartFA.ObjectPoolThread;
import com.BenedictoMatthewJmartFA.Payment;
import com.BenedictoMatthewJmartFA.dbjson.JsonAutowired;
import com.BenedictoMatthewJmartFA.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public abstract class PaymentController implements BasicGetController<Payment> {

    public static final long DELIVERED_LIMIT_MS = 10;
    public static final long ON_DELIVERY_LIMIT_MS = 10;
    public static final long ON_PROGRESS_LIMIT_MS = 10;
    public static final long WAITING_CONF_LIMIT_MS = 10;
    @JsonAutowired(filepath=".scr/main/java/com/test.json", value= Payment.class)
    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;
    static {
        new ObjectPoolThread<Payment>("Payment-Thread", PaymentController::timekeeper);
        poolThread.start();
    }

    @PostMapping("/{id}/accept")
    public boolean accept(int id) {

        return false;
    }

    @PostMapping("/{id}/cancel")
    public boolean cancel(int id) {

        return false;
    }

    @PostMapping("/create")
    public Payment create(@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan) {

        return null;
    }

    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @PostMapping("/{id}/submit")
    public boolean submit(int id, @RequestParam String receipt) {

        return false;
    }

    private static boolean timekeeper(Payment payment) {
        long startTime = System.currentTimeMillis();

        Payment.Record record = payment.history.get(payment.history.size() - 1);
        long time_elapsed = System.currentTimeMillis() - startTime;
        if (record.status == Invoice.Status.WAITING_CONFIRMATION && time_elapsed > WAITING_CONF_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
        } else if (record.status == Invoice.Status.ON_PROGRESS && time_elapsed > ON_PROGRESS_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
        } else if (record.status == Invoice.Status.ON_DELIVERY && time_elapsed > ON_DELIVERY_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "Berhasil"));
        } else if (record.status == Invoice.Status.DELIVERED && time_elapsed > DELIVERED_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "Berhasil"));
        }

        if (record.status == Invoice.Status.FAILED && record.status == Invoice.Status.FINISHED) {
            return true;
        }

        return false;
    }
}
