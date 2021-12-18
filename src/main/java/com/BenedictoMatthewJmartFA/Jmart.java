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
//        try
//        {
//            JsonTable<Payment> table = new JsonTable<>(Payment.class,".scr/main/java/com/paymentTable.json");
//            ObjectPoolThread<Payment> paymentPool = new ObjectPoolThread<Payment>("Thread-PP",Jmart::timekeeper);
//            paymentPool.start();
//            table.forEach(payment->paymentPool.add(payment));
//            while (paymentPool.size() != 0);
//            paymentPool.exit();
//            while(paymentPool.isAlive());
//            System.out.println("Thread exited successfully.");
//            Gson gson =new Gson();
//            table.forEach(payment->
//            {
//                String history = gson.toJson(payment.history);
//                System.out.println(history);
//            });
//        }
//        catch (Throwable t)
//        {
//            t.printStackTrace();
//        }
    }

    public static boolean timekeeper (Payment payment)
    {
        long t_started = System.currentTimeMillis();

        Payment.Record record = payment.history.get(payment.history.size()-1);

        long t_elapsed =System.currentTimeMillis() - t_started;
        if (record.status == Invoice.Status.WAITING_CONFIRMATION && t_elapsed > WAITING_CONF_LIMIT_MS)
        {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "gagal"));
        }
        else if (record.status == Invoice.Status.ON_PROGRESS && t_elapsed > ON_PROGRESS_LIMIT_MS)
        {
            payment.history.add(new Payment.Record(Invoice.Status.FAILED, "gagal"));
        }
        else if (record.status == Invoice.Status.ON_DELIVERY && t_elapsed > ON_DELIVERY_LIMIT_MS)
        {
            payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "terkirim"));
        }
        else if (record.status == Invoice.Status.DELIVERED && t_elapsed > DELIVERED_LIMIT_MS)
        {
            payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "selesai"));
            return true;
        }


        return false;

    }



    }