package com.example.springsecurity.security.domain;

import com.example.springsecurity.security.api.UserResponseDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")

@Data
public class User {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean enabled;
    private String secret;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    protected List<Role> roles = new ArrayList<>();

    public User() {
        this.enabled = false;
    }

    public UserResponseDto toDto() {
        return new UserResponseDto(firstName, lastName);
    }
}
