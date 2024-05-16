package com.pucpr.exercicio.repository;

import com.pucpr.exercicio.entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends CrudRepository<Item,Long> {
    @Query("SELECT i FROM Item i WHERE i.name = :name")
    public Item getItemByName(@Param("name") String name);
}
