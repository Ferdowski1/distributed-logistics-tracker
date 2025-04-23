package com.logistics.routing.dto;

import java.io.Serializable;

public class PackageDTO implements Serializable {
    private String id;
    private String origin;
    private String destination;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
}
