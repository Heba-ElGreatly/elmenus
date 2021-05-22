package com.elmenus.mapper;

import com.elmenus.item.dto.ItemDTO;
import com.elmenus.item.model.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemMapper {

    public Item mapDtoToEntity(ItemDTO itemDTO){
        Item item = new Item();
        item.setItemName(itemDTO.getItemName());
        item.setAvailable(itemDTO.isAvailable());
        item.setPrice(itemDTO.getPrice());
        return item;
    }

    public ItemDTO mapEntityToDTO(Item item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemName(item.getItemName());
        itemDTO.setAvailable(item.isAvailable());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }
}
