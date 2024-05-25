package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.dto.LocacaoDTO;
import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Locacao;
import com.pucpr.biblioteca.entity.MyUserDetails;
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
    private AcervoService acervoService;

    @Autowired
    private LocacaoService locacaoService;

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

    @PostMapping("/emprestar")
    public Locacao emprestarAcervo(@RequestBody LocacaoDTO locacaoDTO) {
        return locacaoService.emprestarAcervo(locacaoDTO);
    }

    @PostMapping("/devolver/{id}")
    public Locacao devolverAcervo(@PathVariable Long id) {
        return locacaoService.devolverAcervo(id);
    }

    @GetMapping(value = "/emprestados")
    public ResponseEntity< Iterable<Acervo> > findAllActives(){
        Iterable<Acervo> acervos = acervoService.findAllEmprestados();
        return ResponseEntity.ok(acervos);
    }
    @PostMapping(value = "/liberarBloquear/{id}", consumes = "application/json")
    public ResponseEntity<Acervo> liberaBloqueiaById(@PathVariable Long id, @RequestBody int status) {
        return ResponseEntity.ok( acervoService.liberarBloquearById(id, status) );
    }
}
