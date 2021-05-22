package com.elmenus.item.service;

import com.elmenus.item.dao.ItemRepository;
import com.elmenus.item.dto.ItemDTO;
import com.elmenus.item.exception.AddNewItemException;
import com.elmenus.item.exception.ItemAlreadyExistException;
import com.elmenus.item.exception.ItemNotFoundException;
import com.elmenus.item.model.Item;
import com.elmenus.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;


    @Override
    public ItemDTO addItem(ItemDTO itemDTO) throws ItemAlreadyExistException, AddNewItemException {
        Optional<Item> item = itemRepository.findByItemName(itemDTO.getItemName());

        if(item.isPresent()){
            throw new ItemAlreadyExistException();
        }
        try {
        Item savedItem = itemRepository.save(itemMapper.mapDtoToEntity(itemDTO));

             return itemMapper.mapEntityToDTO(savedItem);
        }catch(Exception e) {
            e.printStackTrace();
            throw new AddNewItemException();
        }
    }

    @Override
    public List<ItemDTO> findAllItems() throws ItemNotFoundException {
        List<Item> items = itemRepository.findAll();

        return  Optional.of(items.stream().map(item -> itemMapper.mapEntityToDTO(item)).collect(Collectors.toList()))
                .orElseThrow( () -> new ItemNotFoundException());
    }

}
