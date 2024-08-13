package com.ofss.main.domain;

import java.util.Date;

public class Bankslip {
    private int slipId;
    private Date slipDate;
    private int accountId;
    private double amount;

    // Constructor
    public Bankslip(int slipId, Date slipDate, int accountId, double amount) {
        this.slipId = slipId;
        this.slipDate = slipDate;
        this.accountId = accountId;
        this.amount = amount;
    }

    // Getters and setters
    public int getSlipId() {
        return slipId;
    }

    public void setSlipId(int slipId) {
        this.slipId = slipId;
    }

    public Date getSlipDate() {
        return slipDate;
    }

    public void setSlipDate(Date slipDate) {
        this.slipDate = slipDate;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Other methods as needed
}
