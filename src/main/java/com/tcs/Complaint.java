package com.tcs;

public class Complaint {
    private int complaintId;
    private int consumerId;
    private String complaintType;
    private String category;
    private String description;
    private String status;

    // Constructor
    public Complaint(int complaintId, int consumerId, String complaintType, 
                     String category, String description, String status) {
        this.complaintId = complaintId;
        this.consumerId = consumerId;
        this.complaintType = complaintType;
        this.category = category;
        this.description = description;
        this.status = status;
    }

    // Getters
    public int getComplaintId() { return complaintId; }
    public int getConsumerId() { return consumerId; }
    public String getComplaintType() { return complaintType; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
}