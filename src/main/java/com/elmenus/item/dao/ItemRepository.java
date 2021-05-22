package com.elmenus.item.dao;

import com.elmenus.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ItemRepository extends JpaRepository<Item,Integer> {

    Optional<Item> findByItemName(@Param ("itemName") String itemName);
}
