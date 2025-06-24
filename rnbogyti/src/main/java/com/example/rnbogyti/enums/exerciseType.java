package com.example.rnbogyti.enums;

public enum exerciseType {
    UPPER("Upper"),
    LOWER("Lower"),
    CORE_BACK("Core/Back");

    private final String displayName;

    exerciseType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}