package com.acolonia.spring.frontend.model.enums;

public enum Department {
    Finanzas(1),
    Recursos_Humanos(2),
    Marketing(3);

    private final int code;

    Department(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
