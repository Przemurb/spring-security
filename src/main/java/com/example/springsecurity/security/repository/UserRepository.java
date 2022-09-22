package com.example.springsecurity.security.repository;

import com.example.springsecurity.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
