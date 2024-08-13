package com.ofss.main.domain;

import java.util.Date;

public class FixedDeposit {
    private int ticketId;
    private int accountId;
    private Date creationDate;
    private Date expiryDate;
    private String depositType;
    private double depositAmount;

    // Constructor
    public FixedDeposit(int ticketId, int accountId, Date creationDate, Date expiryDate, String depositType, double depositAmount) {
        this.ticketId = ticketId;
        this.accountId = accountId;
        this.creationDate = creationDate;
        this.expiryDate = expiryDate;
        this.depositType = depositType;
        this.depositAmount = depositAmount;
    }

    // Getters and setters
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    // Other methods as needed
}
