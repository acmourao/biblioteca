package com.pucpr.exercicio.config;

import com.pucpr.exercicio.entity.Inventory;
import com.pucpr.exercicio.entity.Item;
import com.pucpr.exercicio.repository.CartRepository;
import com.pucpr.exercicio.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InitialConfig {
    @Bean
    public CommandLineRunner loadInitialData(InventoryRepository inventoryRepository,
                                             CartRepository cartRepository){
        return (args) -> {
            Inventory inventory = new Inventory("Curitiba");
            Item item1 = new Item("Omo", 10, 45.0, "sabao");
            item1.setInventory(inventory);
            inventory.getItems().add(item1);
            Item item2 = new Item("Ariel", 20, 40.0, "sabao");
            item2.setInventory(inventory);
            inventory.getItems().add(item2);
            Item item3 = new Item("Downy", 10, 50.0, "amaciante");
            item3.setInventory(inventory);
            inventory.getItems().add(item3);
            Item item4 = new Item("Toddy", 20, 10.0, "achocolatodo");
            item4.setInventory(inventory);
            inventory.getItems().add(item4);
            Item item5 = new Item("Nescau", 20, 10.0, "achocolatodo");
            item5.setInventory(inventory);
            inventory.getItems().add(item5);
            inventoryRepository.save(inventory);
        };
    }
}
