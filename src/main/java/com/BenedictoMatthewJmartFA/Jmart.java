package com.BenedictoMatthewJmartFA;

import com.BenedictoMatthewJmartFA.dbjson.JsonDBEngine;
import com.BenedictoMatthewJmartFA.dbjson.JsonTable;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class which will run the backend service
 * using SpringBootApplication.
 *
 * @author Benedicto Matthew W
 */

@SpringBootApplication
public class Jmart {
    public static long DELIVERED_LIMIT_MS = 100;
    public static long ON_DELIVERY_LIMIT_MS = 300;
    public static long ON_PROGRESS_LIMIT_MS = 500;
    public static long WAITING_CONF_LIMIT_MS = 700;

    public static void main(String[] args) {
        SpringApplication.run(Jmart.class, args);
        JsonDBEngine.Run(Jmart.class);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
    }

    /** Changes invoice status based on time elapsed
     *
     * @param payment  represents the ongoing payment
     * @return  boolean
     */

    public static boolean timekeeper(Payment payment) {
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