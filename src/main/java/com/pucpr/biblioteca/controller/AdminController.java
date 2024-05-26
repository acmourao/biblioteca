package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.dto.AcervoDTO;
import com.pucpr.biblioteca.dto.LocacaoDTO;
import com.pucpr.biblioteca.dto.MyUserDetails;
import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Categoria;
import com.pucpr.biblioteca.entity.Locacao;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.service.AcervoService;
import com.pucpr.biblioteca.service.CategoriaService;
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

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/user/all")
    public ResponseEntity< Iterable<User> > consultaTodosUsuarios(){
        Iterable<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/user/detail/{idUser}")
    public ResponseEntity<MyUserDetails> consultaDetailPorId(@PathVariable Long idUser){
        return ResponseEntity.ok( userService.findUserDetailById(idUser) );
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<User> consultaUserPorId(@PathVariable Long idUser){
         return ResponseEntity.ok( userService.findById(idUser) );
    }

    @PostMapping("/categoria/add")
    public ResponseEntity<Categoria> addCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok( categoriaService.addCategoria(categoria) );
    }

    @PostMapping("/acervo/add")
    public ResponseEntity<Acervo> addAcervo(@RequestBody AcervoDTO acervoDTO) {
        return ResponseEntity.ok( acervoService.addAcervo(acervoDTO) );
    }

    @PostMapping("/acervo/liberarBloquear/{idAcervo}")
    public ResponseEntity<Acervo> liberaBloqueiaById(@PathVariable Long idAcervo, @RequestBody boolean status) {
        return ResponseEntity.ok( acervoService.setStatusById(idAcervo, status) );
        //bloqueio administrativo, usado na locação e outros impedimentos.
    }

    @PostMapping("/acervo/emprestar")
    public Locacao emprestarAcervo(@RequestBody LocacaoDTO locacaoDTO) {
        return locacaoService.emprestarAcervo(locacaoDTO);
    }

    @PostMapping("/acervo/devolver/{idLocacao}")
    public Locacao devolverAcervo(@PathVariable Long idLocacao) {
        return locacaoService.devolverAcervo(idLocacao);
    }

    @GetMapping("/acervo/indisponiveis")
    public ResponseEntity< Iterable<Acervo> > findAllOut(){
        Iterable<Acervo> acervos = acervoService.findIndisponiveis();
        return ResponseEntity.ok(acervos);
    }

    @GetMapping("/locacao/all")
    public ResponseEntity<Iterable<Locacao>> findPendentesAllUsers() {
        Iterable<Locacao> locacoes = locacaoService.findPendentesAllUsers();
        return ResponseEntity.ok(locacoes);
    }
    @GetMapping("/locacao/{idLocacao}")
    public ResponseEntity<Locacao> findById(@PathVariable Long idLocacao) {
        return ResponseEntity.ok(locacaoService.findById(idLocacao));
    }

    @GetMapping("/locacao/acervo/{idAcervo}")
    public ResponseEntity<Locacao> findAllbyAcervo(@PathVariable Long idAcervo) {
        Locacao locacoes = locacaoService.findByAcervo(idAcervo);
        return ResponseEntity.ok(locacoes);
    }

    @GetMapping("/locacao/user/{idUser}")
    public ResponseEntity<Locacao> findAllByUser(@PathVariable Long idUser) {
        Locacao locacao = locacaoService.findByUser(idUser);
        return ResponseEntity.ok(locacao);
    }
}
