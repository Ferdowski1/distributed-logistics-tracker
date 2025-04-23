package com.logistics.tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PackageEntity {

    @Id
    private String id;

    private String origin;
    private String destination;
    private String status;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
