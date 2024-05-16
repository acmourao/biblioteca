package com.pucpr.exercicio.repository;

import com.pucpr.exercicio.entity.Cart;
import com.pucpr.exercicio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {

    public Cart findCartByActiveAndUser(boolean active, User user);
}
