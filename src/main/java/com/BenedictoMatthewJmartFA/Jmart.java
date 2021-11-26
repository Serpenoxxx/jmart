package com.BenedictoMatthewJmartFA;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.BenedictoMatthewJmartFA.dbjson.JsonDBEngine;
import com.BenedictoMatthewJmartFA.dbjson.JsonTable;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jmart
{

    public static long DELIVERED_LIMIT_MS = 3;
    public static long ON_DELIVERY_LIMIT_MS = 4;
    public static long ON_PROGRESS_LIMIT_MS = 6;
    public static long WAITING_CONF_LIMIT_MS = 7;

    public static void main(String[] args) {
        SpringApplication.run(Jmart.class, args);
        JsonDBEngine.Run(Jmart.class);
        SpringApplication.run(Jmart.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));

/*
        try
        {
            JsonTable<Payment> table = new JsonTable<>(Payment.class,"C:\\Users\\HP\\OneDrive\\kuliah\\Semester 3\\praktikum OOP\\randomPaymentList.json");
            ObjectPoolThread<Payment> paymentPool = new ObjectPoolThread<Payment>("Thread-PP",Jmart::paymentTimekeeper);
            paymentPool.start();
            table.forEach(payment->paymentPool.add(payment));
            while (paymentPool.size() != 0);
            paymentPool.exit();
            while(paymentPool.isAlive());
            System.out.println("Thread exited successfully.");
            //Cek hasil input
            Gson gson =new Gson();
            table.forEach(payment->
            {
                String history = gson.toJson(payment.history);
                System.out.println(history);
            });
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }




        public static boolean timekeeper (Payment payment)
        {
            long t_started = System.currentTimeMillis();

            Record record = payment.history.get(payment.history.size()-1);

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

        }*/

    }




    }