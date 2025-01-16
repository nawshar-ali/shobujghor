package com.shobujghor.app.utility.constants;

public enum Profiles {

    DEV("dev");

    private final String value;

    Profiles(String value) {
        this.value = value;
    }

    public String val() {
        return value;
    }
}
