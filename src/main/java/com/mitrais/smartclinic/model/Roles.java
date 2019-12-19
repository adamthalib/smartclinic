package com.mitrais.smartclinic.model;

public enum Roles {
    ROLE_ADMIN("Admin"),
    ROLE_STAFF("Staff"),
    ROLE_DOCTOR("Doctor");

    private final String displayValue;

    private Roles(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
