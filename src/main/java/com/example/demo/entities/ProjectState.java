package com.example.demo.entities;

public enum ProjectState {

    STARTED ("Started"),
    STOPPED ("Stopped"),
    FINISHED ("Finished");

    private final String name;

    ProjectState(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
