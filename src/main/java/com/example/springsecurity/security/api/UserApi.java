package com.example.springsecurity.security.api;

import com.example.springsecurity.security.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserApi {
    public final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/multi")
    public List<UserResponseDto> getUsersMultiRoles() {
        return userService.getUsersMultiRole();
    }

    @GetMapping("/pre/{username}")
    public UserResponseDto getUserPreAuth(@PathVariable String username) {
        return userService.getUserPreAuth(username);
    }
}
