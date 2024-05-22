package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.entity.MyUserDetails;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.service.JwtUserService;
import com.pucpr.biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private JwtUserService jwtUserService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity< Iterable<User> > consultaTodosUsuarios(){
        Iterable<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<MyUserDetails> consultaDetailPorId(@PathVariable long id){
        return ResponseEntity.ok( userService.findUserDetailById(id) );
    }
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> consultaPorId(@PathVariable long id){
         return ResponseEntity.ok( userService.findUserById(id) );
    }
}
