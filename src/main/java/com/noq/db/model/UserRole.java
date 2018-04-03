package com.noq.db.model;

import java.util.Arrays;

public enum UserRole {
    USER,
    ADMIN;

    private String value;

    public String get() {
        return this.value;
    }

    public static String[] getAllRoles() {
        String[] roles =
                Arrays.toString(UserRole.values()).replaceAll("^.|.$", "").split(", ");
        return roles;
    }
}
