package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.dto.LocacaoDTO;
import com.pucpr.biblioteca.dto.LoginUserDTO;
import com.pucpr.biblioteca.dto.UserDTO;
import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Locacao;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.service.JwtTokenService;
import com.pucpr.biblioteca.service.JwtUserService;
import com.pucpr.biblioteca.service.LocacaoService;
import com.pucpr.biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JwtUserService jwtUserService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private LocacaoService locacaoService;

    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO) {
        return jwtUserService.createUser(userDTO);
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginUserDTO loginUserDto) {
        return jwtUserService.authenticateUser(loginUserDto);
    }

    @GetMapping("/meusdados")
    public ResponseEntity<User> consultaMeusDados() {
        return ResponseEntity.ok(userService.getUserLogado());
    }

    @PostMapping("/emprestar/{id}")
    public Locacao emprestarAcervo(@PathVariable Long id) {
        return locacaoService.emprestarLogado(id);
    }

    @GetMapping("/locacoes")
    public ResponseEntity<Iterable<Acervo>> locacoes() {
        return ResponseEntity.ok(userService.locacoes());
    }

}
