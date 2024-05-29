package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.auth.AuthenticationFacade;
import com.pucpr.biblioteca.dto.LoginUserDTO;
import com.pucpr.biblioteca.dto.UserDTO;
import com.pucpr.biblioteca.entity.Locacao;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.service.JwtTokenService;
import com.pucpr.biblioteca.service.JwtUserService;
import com.pucpr.biblioteca.service.LocacaoService;
import com.pucpr.biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private JwtUserService jwtUserService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private LocacaoService locacaoService;

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(jwtUserService.manterPasswordUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/edit")
    public ResponseEntity<User> editar(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.editar(userDTO));
    }

    @PostMapping("/delete")
    public @ResponseBody String deletar() {
        return userService.deletar();
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginUserDTO loginUserDTO) {
        return jwtUserService.authenticateUser(loginUserDTO);
    }

    @PostMapping("/trocarsenha")
    public User trocarSenha(@RequestBody String password) {
        User user = authenticationFacade.getUser();
        user.setPassword(password);
        return jwtUserService.manterPasswordUser(user);
    }

    @GetMapping("/meusdados")
    public ResponseEntity<User> consultaMeusDados() {
        return ResponseEntity.ok(authenticationFacade.getUser());
    }

    @PostMapping("/locar/{idAcervo}")
    public ResponseEntity<Locacao> emprestarAcervoUserLogado(@PathVariable Long idAcervo) {
        return ResponseEntity.ok(locacaoService.locarAcervo(idAcervo));
    }

    @PostMapping("/devolver/{idAcervo}")
    public ResponseEntity<Locacao> devolverAcervo(@PathVariable Long idAcervo) {
        return ResponseEntity.ok(userService.devolverAcervoUserLogado(idAcervo));
    }

    @GetMapping("/locacoes")
    public ResponseEntity< Iterable<Locacao>> findLocacoesByUser() {
        User user = userService.getUserLogado();
        return ResponseEntity.ok(locacaoService.findByUserAndActive(user));
    }

}
