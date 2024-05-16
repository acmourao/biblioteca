package com.pucpr.exercicio.controller;

import com.pucpr.exercicio.entity.Cart;
import com.pucpr.exercicio.entity.Item;
import com.pucpr.exercicio.service.CartService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
Objetivo: Implementar um CRUD completo para um Array Map de itens no Spring Boot, utilizando Java.
Funcionalidades:
* Listar todos os itens: Exibir todos os itens presentes no Array Map.
* Buscar um item por ID: Obter um item específico pelo seu índice no Array Map.
* Adicionar um novo item: Inserir um novo item no final do Array Map.
* Atualizar um item: Substituir um item existente no Array Map pelo índice.
* Remover um item: Excluir um item do Array Map pelo seu índice.
Instruções:
1. Crie um projeto Spring Boot com as dependências Web.
2. Defina uma classe Item com os atributos nome, quantidade, valor e descritivo.
3. Implemente um Controller RESTful ItemController com os seguintes endpoints:
    * GET /api/itens: Retorna todos os itens do Array List.
    * GET /api/itens/{id}: Retorna o item com o índice especificado (id).
    * POST /api/itens: Adiciona um novo item ao Array Map.
    * PUT /api/itens/{id}: Atualiza o item com o índice especificado (id).
    * DELETE /api/itens/{id}: Remove o item com o índice especificado (id).
4. Teste a API com ferramentas como Postman ou curl.
 */
/* Parte 2
Implemente um CRUD completo para um Array List de itens no Spring Boot, utilizando Java e um banco de dados SQL.
 */
/* Parte 3
Implemente um CRUD de compras que tenha um relacionamento One-To-Many com a entidade Item.
 */
@RestController
@RequestMapping("api")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<Cart> createCart() {
        return new ResponseEntity<>(cartService.createCart(),
                HttpStatus.CREATED);
    }
    @GetMapping("/cart")
    public ResponseEntity<Cart> getAll() {
        return ResponseEntity.ok(cartService.findActiveFromUser());
    }


    @DeleteMapping("/cart")
    public ResponseEntity<Void> delete() {
        cartService.deleteActiveFromUser();
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/cart/item/{itemId}", consumes = "application/json")
    public ResponseEntity<Item> addItemToCart(@PathVariable long itemId, @RequestBody int quantity) {
        return new ResponseEntity<>(cartService.addItemToCart(itemId, quantity), HttpStatus.CREATED);
    }

    /*@DeleteMapping("/cart/{cartId}/item/{itemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        cartService.removeItemFromCart(cartId, itemId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/cart/{cartId}/item")
    public ResponseEntity<Iterable<Item>> findAllItemsFromCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.findAllItemsFromCart(cartId));
    }

    @GetMapping("/cart/{cartId}/item/{itemId}")
    public ResponseEntity<Item> findItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        return ResponseEntity.ok(cartService.findItemFromCart(cartId, itemId));
    }*/
}
