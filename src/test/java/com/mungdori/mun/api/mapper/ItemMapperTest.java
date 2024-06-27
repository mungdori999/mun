package com.mungdori.mun.api.mapper;

import com.mungdori.mun.api.domain.item.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ItemMapperTest {

    @Autowired
    private ItemMapper itemMapper;


    @Test
    void insertItem() {
        Item item = new Item();
        item.setItemName("shiba");
        item.setGltf("shiba/scene.gltf");
        itemMapper.insertItem(item);
    }

}