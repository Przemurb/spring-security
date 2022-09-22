package com.example.springsecurity.security.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;



}
