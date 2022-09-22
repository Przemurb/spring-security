package com.example.springsecurity.security.domain;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");
    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }
}
