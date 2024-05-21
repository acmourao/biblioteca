package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.dto.LoginUserDTO;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pucpr.biblioteca.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JwtUserService userService;

    @Autowired
    private UserRepository repository;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginUserDTO loginUserDto) {
        return userService.authenticateUser(loginUserDto);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> consultaPorId(@PathVariable Long id){
        return ResponseEntity.ok(repository.findById(id).get());
    }
}
