package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.dto.LoginUserDTO;
import com.pucpr.biblioteca.entity.MyUserDetails;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.service.JwtUserService;
import com.pucpr.biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pucpr.biblioteca.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JwtUserService jwtUserService;

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return jwtUserService.createUser(user);
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginUserDTO loginUserDto) {
        return jwtUserService.authenticateUser(loginUserDto);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<MyUserDetails> consultaPorId(@PathVariable Long id){
        MyUserDetails userDetails = (MyUserDetails) userService.loadUserById(id);
        return ResponseEntity.ok(userDetails);
    }
}
