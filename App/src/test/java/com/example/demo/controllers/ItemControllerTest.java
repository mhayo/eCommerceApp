package com.example.demo.controllers;


import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemControllerTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemController itemController;
    @Test
    @Transactional
    public void getItemsTest(){

        ResponseEntity<List<Item>> items = itemController.getItems();

        Assert.assertNotNull(items);
        Assert.assertEquals(200,items.getStatusCodeValue());

        // Compare with result from itemRepository
        Assert.assertEquals(itemRepository.findAll().size(), Objects.requireNonNull(items.getBody()).size());

    }

    @Test
    @Transactional
    public void getItemByIdTest(){

        Item itemId = Objects.requireNonNull(itemController.getItemById(1l).getBody());

        Item itemRef = itemRepository.getOne(1L);

       // testing overwritten hashcode method
        Assert.assertTrue(itemId.hashCode() == itemRef.hashCode());

        // testing overwritten equals method
        Assert.assertTrue(itemId.equals(itemRef));

        // negative test

        ResponseEntity<Item> unknown = itemController.getItemById(5l);
        Assert.assertEquals(404,unknown.getStatusCodeValue());

    }

    @Test
    @Transactional
    public void getItemsByName(){

        List <Item> items = itemController.getItemsByName("Square Widget").getBody();

        List <Item> itemsRef = itemRepository.findByName("Square Widget");

        Assert.assertTrue(items.get(0).equals(itemsRef.get(0)));

        // negative test

        ResponseEntity<List<Item>> unknown = itemController.getItemsByName("Unknown Item");
        Assert.assertEquals(404,unknown.getStatusCodeValue());
    }

}
