package com.aceforeverd;

public enum Currency {
    RMB("RMB"),
    USD("USD"),
    JPY("JPY"),
    EUR("EUR"),
    UNKNOWN("UNKNOWN");

    private String name;

    Currency(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public static Currency getCurrency(String name) {
        if (name.equals("RMB")) {
            return RMB;
        }
        if (name.equals("USD")) {
            return USD;
        }
        if (name.equals("JPY")) {
            return JPY;
        }
        if (name.equals("EUR")) {
            return EUR;
        }
        else {
            return UNKNOWN;
        }
    }
}
