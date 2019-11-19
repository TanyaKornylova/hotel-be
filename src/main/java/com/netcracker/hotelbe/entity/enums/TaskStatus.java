package com.netcracker.hotelbe.entity.enums;

public enum TaskStatus {

    OPEN("OPEN"),
    Started("Started"),
    Complete("Complete"),
    Failed("Failed"),
    Canceled("Canceled");

    private String fullName;

    TaskStatus(String fullName) {
        this.fullName = fullName;
    }

    String getFullName() {
        return fullName;
    }
}
