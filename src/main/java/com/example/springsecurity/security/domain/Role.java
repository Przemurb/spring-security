package com.example.springsecurity.security.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    )
    private List<Privilege> privileges = new ArrayList<>();
}
