package com.netcracker.hotelbe.model;

import java.io.Serializable;

public enum ApartmentStatus implements Serializable {
    ReadyToCheckIn("ReadyToCheckIn"),
    OnRepair("OnRepair"),
    NeedCleaning("NeedCleaning"),
    Occupied("Occupied");

    private String fullName;

    ApartmentStatus(String fullName) {
        this.fullName = fullName;
    }

    String getFullName() {
        return fullName;
    }
}
