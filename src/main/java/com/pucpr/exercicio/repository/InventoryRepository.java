package com.pucpr.exercicio.repository;

import com.pucpr.exercicio.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository  extends CrudRepository<Inventory, Long> {
}
