package com.pucpr.biblioteca.repository;

import com.pucpr.biblioteca.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String nome);
}
