package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.dto.LocacaoDTO;
import com.pucpr.biblioteca.dto.LoginUserDTO;
import com.pucpr.biblioteca.dto.UserDTO;
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

    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return jwtUserService.createUser(user);
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginUserDTO loginUserDto) {
        return jwtUserService.authenticateUser(loginUserDto);
    }

    @GetMapping("/meusdados")
    public ResponseEntity<User> consultaMeusDados() {
        return ResponseEntity.ok(userService.getUserLogado());
    }

    @PostMapping("/edit")
    public ResponseEntity<User> editar(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.editar(userDTO));
    }

    @PostMapping("/delete")
    public void deletar() {
        userService.deletar();
    }

    @PostMapping("/emprestar/{idAcervo}")
    public Locacao emprestarAcervoUserLogado(@PathVariable Long idAcervo) {
        return locacaoService.emprestarAcervoUserLogado(idAcervo);
    }

    @GetMapping("/locacoes")
    public ResponseEntity< Iterable<Locacao>> findLocacoesByUser() {
        User user = userService.getUserLogado();
        Iterable<Locacao> locacao = locacaoService.findByUser(user);
        return ResponseEntity.ok(locacao);
    }

    @PostMapping("/devolver/{idLocacao}")
    public Locacao devolverAcervo(@PathVariable Long idLocacao) {
        return locacaoService.devolverAcervo(idLocacao);
    }

}
