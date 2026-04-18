/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chiong;

/**
 *
 * @author Admin
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type; // deposit or withdraw
    private double amount;
    private String dateTime;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dateTime = LocalDateTime.now().format(dtf);
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String toString() {
        return dateTime + " | " + type + " | PHP " + String.format("%.2f", amount);
    }
}
