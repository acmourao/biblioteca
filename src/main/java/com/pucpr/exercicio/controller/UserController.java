package com.pucpr.exercicio.controller;

import com.pucpr.exercicio.dto.LoginUserDTO;
import com.pucpr.exercicio.entity.User;
import com.pucpr.exercicio.service.JwtUserService;
import com.pucpr.exercicio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pucpr.exercicio.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JwtUserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginUserDTO loginUserDto) {
        return userService.authenticateUser(loginUserDto);
    }
}
