package com.ofss.main.domain;

import java.util.Date; // Import Date class for opening_date

public class Account {
    private int accountId;
    private int customerId;
    private Date openingDate;
    private String accountType;
    private double minBalance;
    private double currentBalance;
    private boolean overdraftAvailable;

    // Constructor
    public Account(int accountId, int customerId, Date openingDate, String accountType, 
                   double minBalance, double currentBalance, boolean overdraftAvailable) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.openingDate = openingDate;
        this.accountType = accountType;
        this.minBalance = minBalance;
        this.currentBalance = currentBalance;
        this.overdraftAvailable = overdraftAvailable;
    }

    // Getters and setters
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public boolean isOverdraftAvailable() {
        return overdraftAvailable;
    }

    public void setOverdraftAvailable(boolean overdraftAvailable) {
        this.overdraftAvailable = overdraftAvailable;
    }

    // Other methods as needed
}
