package org.example.demohtmlweb.enums;

public enum Gender {
    Female("ผู้หญิง"),
    Male("ผู้ชาย"),
    Another("อื่นๆ ไม่ระบุ");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
