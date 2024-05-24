package com.pucpr.biblioteca.repository;

import com.pucpr.biblioteca.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String nome);
}
