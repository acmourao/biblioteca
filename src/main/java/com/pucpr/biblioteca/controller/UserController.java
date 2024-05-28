package com.pucpr.biblioteca.controller;

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
        return jwtUserService.manterPasswordUser(user);
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
    public String authenticateUser(@RequestBody LoginUserDTO loginUserDto) {
        return jwtUserService.authenticateUser(loginUserDto);
    }

    @PostMapping("/trocarsenha")
    public User trocarSenha(@RequestBody String password) {
        User user = userService.getUserLogado();
        user.setPassword(password);
        return jwtUserService.manterPasswordUser(user);
    }

    @GetMapping("/meusdados")
    public ResponseEntity<User> consultaMeusDados() {
        return ResponseEntity.ok(userService.getUserLogado());
    }

    @PostMapping("/emprestar/{idAcervo}")
    public ResponseEntity<Locacao> emprestarAcervoUserLogado(@PathVariable Long idAcervo) {
        return ResponseEntity.ok(locacaoService.emprestarAcervo(idAcervo));
    }

    @PostMapping("/devolver/{idAcervo}")
    public ResponseEntity<Locacao> devolverAcervo(@PathVariable Long idAcervo) {
        return ResponseEntity.ok(locacaoService.devolverAcervo(idAcervo));
    }

    @GetMapping("/locacoes")
    public ResponseEntity< Iterable<Locacao>> findLocacoesByUser() {
        User user = userService.getUserLogado();
        return ResponseEntity.ok(locacaoService.findByUserAndActive(user));
    }

}
