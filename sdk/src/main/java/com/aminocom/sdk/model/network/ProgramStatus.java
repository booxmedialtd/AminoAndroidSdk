package com.aminocom.sdk.model.network;


public enum ProgramStatus {
    RECORDED("RECORDED"),
    SCHEDULED("SCHEDULED"),
    RECORDING("RECORDING");

    private String value;

    ProgramStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static ProgramStatus fromString(String text) {
        for (ProgramStatus b : ProgramStatus.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }

        return null;
    }
}