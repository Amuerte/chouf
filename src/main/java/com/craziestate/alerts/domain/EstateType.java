package com.craziestate.alerts.domain;

import java.util.Arrays;

public enum EstateType {
    FLAT('F'),
    HOUSE('H');

    private char code;

    private EstateType(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static EstateType fromCode(char type) {
        return Arrays.stream(EstateType.values()).filter(t -> t.code == type).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Wrong estate type"));
    }
}
