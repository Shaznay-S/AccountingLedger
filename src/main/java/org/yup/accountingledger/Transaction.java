package org.yup.accountingledger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

public String date;
public String time;
public String dateTime;
private String description;
private String vendor;
private float amount;

    public Transaction(String date, String time, String description, String vendor, float amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

//    public Transaction(String description, String vendor, float amount) {
//        this.description = description;
//        this.vendor = vendor;
//        this.amount = amount;
//    }

//    public Transaction(LocalDate transactionDate, LocalTime transactionTime, String transactionDescription,
//                       String transactionVendor, float transactionAmount) {
//    }

//    public String getDateTime() {
//        LocalDateTime dateTime = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm:ss");
//        String formattedDateTime = dateTime.format(formatter);
//
//        return formattedDateTime;
//    }

    public String getDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

        return formattedDate;
    }

    public String getTime() {
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(formatter);

        return formattedTime;
    }
    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public float getAmount() {

        return amount;
    }


}
