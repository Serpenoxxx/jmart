package com.BenedictoMatthewJmartFA.controller;

import com.BenedictoMatthewJmartFA.*;
import com.BenedictoMatthewJmartFA.dbjson.JsonAutowired;
import com.BenedictoMatthewJmartFA.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls all requests related to payment with RestController
 * Implements BasicGetController
 *
 * @author Benedicto Matthew W
 */

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {

    public static final long DELIVERED_LIMIT_MS = 10;
    public static final long ON_DELIVERY_LIMIT_MS = 10;
    public static final long ON_PROGRESS_LIMIT_MS = 10;
    public static final long WAITING_CONF_LIMIT_MS = 10;

    @JsonAutowired(filepath=".scr/main/java/com/payment.json", value= Payment.class)
    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

//    static {
//        new ObjectPoolThread<Payment>("Payment-Thread", PaymentController::timekeeper);
//        poolThread.start();
//    }

    /**
     * Accepts the payment of a certain item if status is WAITING_CONFIRMATION.
     * Changes status to ON_PROGRESS if accepted.
     *
     * @param  id checks the item id.
     * @return  boolean representing status of item.
     */

    @PostMapping("/{id}/accept")
    public boolean accept(@PathVariable int id) {
        for (Payment payment : getJsonTable()) {
            if (payment.id == id && (payment.history.get(payment.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION)) {
                payment.history.add(new Payment.Record(Invoice.Status.ON_PROGRESS, "On Progress"));
                return true;
            }
        }
        return false;
    }

    /**
     * Cancels the payment of a certain item if status is WAITING_CONFIRMATION.
     * Changes status to CANCELLED if canceled.
     *
     * @param  id checks the item id.
     * @return  boolean representing status of item.
     */

    @PostMapping("/{id}/cancel")
    public boolean cancel(@PathVariable int id) {
        for (Payment payment : getJsonTable()) {
            if (payment.id == id && (payment.history.get(payment.history.size()-1).status == Invoice.Status.WAITING_CONFIRMATION)) {
                payment.history.add(new Payment.Record(Invoice.Status.CANCELLED, "Canceled"));
                return true;
            }
        }
        return false;
    }

    /** Creates the request of a payment.
     * Will deduct the account balance based on final price.
     *
     * @param  buyerId represents the id of purchaser
     * @param  productId represents the id of purchased item
     * @param  productCount represents the quantity of purchased items
     * @param  shipmentAddress represents the shipment address
     * @param  shipmentPlan represents the used shipment plan
     * @return  payment class
     */

    @PostMapping("/create")
    public Payment create(
            @RequestParam int buyerId,
            @RequestParam int productId,
            @RequestParam int productCount,
            @RequestParam String shipmentAddress,
            @RequestParam byte shipmentPlan
    ) {
        Account account = Algorithm.<Account>find(AccountController.accountTable, (e) -> e.id == buyerId);
        Product product = Algorithm.<Product>find(ProductController.productTable, (e) -> e.id == productId);
        if ((account != null) && (product != null)) {
            Payment payment = new Payment(buyerId, productId, productCount, null);
            double totalPriceToPay = payment.getTotalPay(product);

            if (account.balance >= totalPriceToPay ) {
                Shipment shipmentDetail = new Shipment(shipmentAddress, 0, shipmentPlan, null);
                account.balance -= totalPriceToPay * productCount;
                payment = new Payment(buyerId, productId, productCount, shipmentDetail);
                payment.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Awaiting Confirmation"));
                paymentTable.add(payment);
//                poolThread.add(payment);
                return payment;
            }
        }
        return null;
    }

    /** Changes the status to ON_DELIVERY
     *
     * @param  id represents the id
     * @param  receipt represents the receipt of purchased item
     * @return  boolean
     */

    @PostMapping("/{id}/submit")
    public boolean submit(@PathVariable int id, @RequestParam String receipt) {
        for (Payment payment : getJsonTable()) {
            if (payment.id == id && (payment.history.get(payment.history.size()-1).status == Invoice.Status.ON_PROGRESS)) {
                if (!receipt.isBlank()) {
                    payment.shipment.receipt = receipt;
                    payment.history.add(new Payment.Record(Invoice.Status.ON_DELIVERY, "In Progress"));
                    return true;
                }
            }
        }
        return false;
    }

    @GetMapping("/{id}/getPayment")
    public List<Payment> getPayment(@PathVariable int id){
        List<Payment> filtered = new ArrayList<>();
        JsonTable<Product> product = ProductController.productTable;
        int tempId = 0;
        for(Product prod: product){
            if(prod.accountId == id){
                tempId = prod.id;
                for(Payment payment:getJsonTable()){
                    if(payment.productId == tempId){
                        filtered.add(payment);
                    }
                }
            }
        }
        return filtered;
    }

    /** Changes invoice status based on time elapsed
     *
     * @param payment  represents the ongoing payment
     * @return  boolean
     */

    private static boolean timekeeper(Payment payment) {
        long startTime = System.currentTimeMillis();

        Payment.Record record = payment.history.get(payment.history.size() - 1);
        long time_elapsed = System.currentTimeMillis() - startTime;
        if (record.status == Invoice.Status.WAITING_CONFIRMATION && time_elapsed > WAITING_CONF_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Failed"));
        } else if (record.status == Invoice.Status.ON_PROGRESS && time_elapsed > ON_PROGRESS_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Failed"));
        } else if (record.status == Invoice.Status.ON_DELIVERY && time_elapsed > ON_DELIVERY_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "Success"));
        } else if (record.status == Invoice.Status.DELIVERED && time_elapsed > DELIVERED_LIMIT_MS) {
            payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "Success"));
        }
        if (record.status == Invoice.Status.FAILED && record.status == Invoice.Status.FINISHED) {
            return true;
        }
        return false;
    }
}



