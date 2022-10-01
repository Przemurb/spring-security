package com.example.springsecurity.security.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponseDto {
    private final String firstName;
    private final String lastName;
}
