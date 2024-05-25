package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.dto.LocacaoDTO;
import com.pucpr.biblioteca.dto.MyUserDetails;
import com.pucpr.biblioteca.entity.Locacao;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.service.AcervoService;
import com.pucpr.biblioteca.service.LocacaoService;
import com.pucpr.biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private LocacaoService locacaoService;

    @Autowired
    private AcervoService acervoService;

    @GetMapping(value = "/users")
    public ResponseEntity< Iterable<User> > consultaTodosUsuarios(){
        Iterable<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping(value = "/user/detail/{id}")
    public ResponseEntity<MyUserDetails> consultaDetailPorId(@PathVariable Long id){
        return ResponseEntity.ok( userService.findUserDetailById(id) );
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> consultaUserPorId(@PathVariable Long id){
         return ResponseEntity.ok( userService.findById(id) );
    }

    @PostMapping("/emprestarAcervo")
    public Locacao emprestarAcervo(@RequestBody LocacaoDTO locacaoDTO) {
        return locacaoService.emprestarAcervo(locacaoDTO);
    }

    @PostMapping("/devolverAcervo/{id}")
    public Locacao devolverAcervo(@PathVariable Long id) {
        return locacaoService.devolverAcervo(id);
    }
}
