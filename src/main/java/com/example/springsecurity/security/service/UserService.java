package com.example.springsecurity.security.service;

import com.example.springsecurity.security.api.UserResponseDto;
import com.example.springsecurity.security.domain.User;
import com.example.springsecurity.security.repository.UserRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasRole('ROLE_API_READ_PRIVILEGE')")
    public List<UserResponseDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(User::toDto)
                .toList();
    }

    @PreAuthorize("hasRole('ROLE_API_READ_PRIVILEGE') or hasRole('ROLE_ADMIN')")
    public List<UserResponseDto> getUsersMultiRole() {
        return userRepository.findAll()
                .stream()
                .map(User::toDto)
                .toList();
    }

    @PostAuthorize("#username == authentication.principal.username")
    public UserResponseDto getUserPreAuth(String username) {
        return userRepository.findByEmail(username).toDto();
    }
}
