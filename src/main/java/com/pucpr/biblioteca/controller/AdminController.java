package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.dto.AcervoDTO;
import com.pucpr.biblioteca.dto.LocacaoDTO;
import com.pucpr.biblioteca.dto.MyUserDetails;
import com.pucpr.biblioteca.dto.UserDTO;
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
    private CategoriaService categoriaService;

    @Autowired
    private AcervoService acervoService;

    @GetMapping("/user/all")
    public ResponseEntity< Iterable<User> > consultaTodosUsuarios(){
        Iterable<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/detail/{idUser}")
    public ResponseEntity<MyUserDetails> consultaDetailById(@PathVariable Long idUser){
        return ResponseEntity.ok( userService.findUserDetailById(idUser) );
    }

    @GetMapping("/user/bloquear/{idUser}")
    public ResponseEntity<MyUserDetails> bloquearUserById(@PathVariable Long idUser){
        return ResponseEntity.ok( userService.bloquearUserById(idUser) );
    }

    @GetMapping("/user/edit/{idUser}")
    public ResponseEntity<User> editarUserPorId(@PathVariable Long idUser, @RequestBody UserDTO userDTO){
         return ResponseEntity.ok( userService.editarById(idUser, userDTO) );
    }

    @PostMapping("/categoria/add")
    public ResponseEntity<Categoria> addCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok( categoriaService.addCategoria(categoria) );
    }

    @PostMapping("/categoria/edit")
    public ResponseEntity<Categoria> editCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.editar(categoria));
    }

    @PostMapping("/categoria/delete/{id}")
    public @ResponseBody String deleteCategoria(@PathVariable int id) {
        return categoriaService.deletar(id);
    }

    @PostMapping("/acervo/add")
    public ResponseEntity<Acervo> addAcervo(@RequestBody AcervoDTO acervoDTO) {
        return ResponseEntity.ok( acervoService.add(acervoDTO) );
    }

    @PostMapping("/acervo/edit/{idAcervo}")
    public ResponseEntity<Acervo> editAcervo(@PathVariable Long idAcervo, @RequestBody AcervoDTO acervoDTO) {
        return ResponseEntity.ok(acervoService.editar(idAcervo, acervoDTO));
    }

    @PostMapping("/acervo/delete/{id}")
    public @ResponseBody String deleteAcervo(@PathVariable Long id) {
        return acervoService.deletar(id);
    }

    @PostMapping("/acervo/emprestar")
    public ResponseEntity<Locacao> emprestarAcervo(@RequestBody LocacaoDTO locacaoDTO) {
        return ResponseEntity.ok( locacaoService.emprestarAcervo(locacaoDTO) );
    }

    @PostMapping("/acervo/devolver/{idAcervo}")
    public ResponseEntity<Locacao> devolverAcervo(@PathVariable Long idAcervo) {
        return ResponseEntity.ok( locacaoService.devolverAcervo(idAcervo) );
    }

    @GetMapping("/locacao/pendentes")
    public ResponseEntity<Iterable<Locacao>> findPendentesAllUsers() {
        return ResponseEntity.ok(locacaoService.findPendentesAllUsers());
    }

    @GetMapping("/locacao/{idLocacao}")
    public ResponseEntity<Locacao> findLocacaoById(@PathVariable Long idLocacao) {
        return ResponseEntity.ok(locacaoService.findById(idLocacao));
    }

    @GetMapping("/locacao/acervo/{idAcervo}")
    public ResponseEntity<Locacao> findAllLocacaoByAcervo(@PathVariable Long idAcervo) {
        return ResponseEntity.ok(locacaoService.findByAcervoId(idAcervo));
    }

    @GetMapping("/locacao/user/{idUser}")
    public ResponseEntity< Iterable<Locacao>> findLocacoesByUser(@PathVariable Long idUser) {
        return ResponseEntity.ok(locacaoService.findByUserId(idUser));
    }
}
