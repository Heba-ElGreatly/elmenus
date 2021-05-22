package com.elmenus.item.service;

import com.elmenus.item.dto.ItemDTO;
import com.elmenus.item.exception.AddNewItemException;
import com.elmenus.item.exception.ItemAlreadyExistException;
import com.elmenus.item.exception.ItemNotFoundException;

import java.util.List;

public interface ItemService {

    ItemDTO addItem(ItemDTO item) throws ItemAlreadyExistException, AddNewItemException;
    List<ItemDTO> findAllItems() throws ItemNotFoundException;

}
