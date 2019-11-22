package com.netcracker.hotelbe.entity.enums;

import java.io.Serializable;

public enum UserRole implements Serializable {

    Client("Client"),
    Worker("Worker"),
    Manager("Manager"),
    Administrator("Administrator");


    private String fullName;

    UserRole(String fullName) {
        this.fullName = fullName;
    }

    String getFullName() {
        return fullName;
    }
}
