package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.entity.MyUserDetails;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity< Iterable<User> > consultaTodosUsuarios(){
        Iterable<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<MyUserDetails> consultaDetailPorId(@PathVariable Long id){
        return ResponseEntity.ok( userService.findUserDetailById(id) );
    }
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> consultaUserPorId(@PathVariable Long id){
         return ResponseEntity.ok( userService.findById(id) );
    }
}
