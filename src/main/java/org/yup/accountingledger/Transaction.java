package org.yup.accountingledger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
public String dateTime;
private String description;
private String vendor;
private float amount;

    public Transaction(String description, String vendor, float amount) {
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);

        return formattedDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

}
