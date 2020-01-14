package com.example.demo.entities;

public enum TaskState {
    ASSIGNED ("Assigned"),
    CONFIRMED ("Confirmed"),
    APPROVED ("Approved");

    private final String name;

    TaskState(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
