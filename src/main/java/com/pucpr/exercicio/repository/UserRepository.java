package com.pucpr.exercicio.repository;

import com.pucpr.exercicio.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String nome);
}
