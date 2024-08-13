package com.ofss.main.domain;

import java.util.Date;

public class Cheque {
    private int chequeId;
    private int payerId;
    private int payeeId;
    private double amount;
    private Date chequeDate;
    private String chequeStatus;
    private String payerName;

    // Constructor
    public Cheque(int chequeId, int payerId, int payeeId, double amount, Date chequeDate, String chequeStatus, String payerName) {
        this.chequeId = chequeId;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
        this.chequeDate = chequeDate;
        this.chequeStatus = chequeStatus;
        this.payerName = payerName;
    }

    // Getters and setters
    public int getChequeId() {
        return chequeId;
    }

    public void setChequeId(int chequeId) {
        this.chequeId = chequeId;
    }

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }

    public int getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(int payeeId) {
        this.payeeId = payeeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(Date chequeDate) {
        this.chequeDate = chequeDate;
    }

    public String getChequeStatus() {
        return chequeStatus;
    }

    public void setChequeStatus(String chequeStatus) {
        this.chequeStatus = chequeStatus;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    // Other methods as needed
}
