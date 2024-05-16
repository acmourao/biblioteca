package com.pucpr.exercicio.service;

import com.pucpr.exercicio.auth.AuthenticationFacade;
import com.pucpr.exercicio.entity.Cart;
import com.pucpr.exercicio.entity.Item;
import com.pucpr.exercicio.entity.User;
import com.pucpr.exercicio.repository.CartRepository;
import com.pucpr.exercicio.repository.InventoryRepository;
import com.pucpr.exercicio.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Transactional
    public Cart createCart() {
        User user = authenticationFacade.getUser();
        if(cartRepository.findCartByActiveAndUser(true,user) != null){
            throw new RuntimeException("Cart already exists");
        }
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setDate(new Date());
        cart.setActive(true);
        return cartRepository.save(cart);
    }
    
    public Cart findActiveFromUser() {
        User user = authenticationFacade.getUser();
        return cartRepository.findCartByActiveAndUser(true,user);
    }

    public Iterable<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart findById(Long id) {
        return cartRepository
                .findById(id)
                .orElse(null);
    }

    @Transactional
    public void deleteActiveFromUser() {
        Cart cart = findActiveFromUser();
        if(cart == null){
            throw new RuntimeException("Cart does not exists");
        }
        cartRepository.delete(cart);
    }

    @Transactional
    public Item addItemToCart(long itemId, int quantity) {
        Item itemInventory = itemRepository.findById(itemId).orElse(null);
        if (itemInventory == null) {
            throw new RuntimeException("Item not found in inventory");
        }
        if (itemInventory.getQuantity() < quantity) {
            throw new RuntimeException("Not enough items in inventory");
        }
        Cart cart = findActiveFromUser();
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }
        itemInventory.setQuantity(itemInventory.getQuantity() - quantity);
        itemRepository.save(itemInventory);
        Item item = new Item();
        item.setName(itemInventory.getName());
        item.setQuantity(quantity);
        item.setValue(itemInventory.getValue());
        item.setDescription(itemInventory.getDescription());
        item.setCart(cart);
        return itemRepository.save(item);
    }
    @Transactional
    public void removeItemFromCart(Long cartId, Long itemId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }
        Item item = itemRepository.findById(itemId).orElse(null);
        if (item == null) {
            throw new RuntimeException("Item not found");
        }
        Item itemInventory = null;
        throw new RuntimeException("Item not found in inventory");
    }

    public Iterable<Item> findAllItemsFromCart(Long cartId) {
        return cartRepository
                .findById(cartId)
                .orElseThrow().getItems();
    }
}
