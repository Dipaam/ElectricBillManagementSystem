package com.tcs;

public class Customer {
    private int consumerId;
    private String customerName;
    private String email;
    private String mobileNumber;
    private String status;

    public Customer(int consumerId, String customerName, String email, String mobileNumber, String status) {
        this.consumerId = consumerId;
        this.customerName = customerName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.status = status;
    }

    // Getters
    public int getConsumerId() { return consumerId; }
    public String getCustomerName() { return customerName; }
    public String getEmail() { return email; }
    public String getMobileNumber() { return mobileNumber; }
    public String getStatus() { return status; }
}