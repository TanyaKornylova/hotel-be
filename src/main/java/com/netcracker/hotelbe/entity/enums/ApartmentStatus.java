package com.netcracker.hotelbe.entity.enums;

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
