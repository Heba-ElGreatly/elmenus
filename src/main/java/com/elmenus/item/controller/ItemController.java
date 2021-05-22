package com.elmenus.item.controller;


import com.elmenus.item.dto.ItemDTO;
import com.elmenus.item.exception.AddNewItemException;
import com.elmenus.item.exception.ItemAlreadyExistException;
import com.elmenus.item.exception.ItemNotFoundException;
import com.elmenus.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    ResponseEntity<List<ItemDTO>> findAllItems () throws ItemNotFoundException {
        return new ResponseEntity<>(itemService.findAllItems(),HttpStatus.OK);
    }

    @PostMapping(consumes="application/json")
    ResponseEntity<?> addItem(@RequestBody ItemDTO item) throws ItemAlreadyExistException, AddNewItemException {
        try {
            ItemDTO serviceResult = itemService.addItem(item);
            return new ResponseEntity(serviceResult, CREATED);

        }catch (ItemAlreadyExistException e){
            e.printStackTrace();
            throw  new ItemAlreadyExistException();
        }catch (AddNewItemException e){
            e.printStackTrace();
            throw new AddNewItemException();
        }
//        return new ResponseEntity(itemService.addItem(item), CREATED);
    }

}
