package com.ofss.main.domain;

import java.util.Date;

public class Transaction {
    private int transactionId;
    private Date dateTime;
    private String transactionType;
    private double amount;
    private int accountId;

    // Constructor
    public Transaction(int transactionId, Date dateTime, String transactionType, double amount, int accountId) {
        this.transactionId = transactionId;
        this.dateTime = dateTime;
        this.transactionType = transactionType;
        this.amount = amount;
        this.accountId = accountId;
    }

    // Getters and setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    // Other methods as needed
}
