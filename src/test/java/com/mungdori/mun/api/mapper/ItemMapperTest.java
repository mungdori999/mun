package com.mungdori.mun.api.mapper;

import com.mungdori.mun.api.domain.item.Item;
import com.mungdori.mun.api.domain.item.TimeGageDTO;
import com.mungdori.mun.api.service.item.GageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ItemMapperTest {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private GageService gageService;


    @Test
    void insertItem() {
        Item item = new Item();
        item.setItemName("shiba");
        item.setGltf("shiba/scene.gltf");
        itemMapper.insertItem(item);
    }

    @Test
    void insertGage() {
        gageService.setStartGage(6l, 1l);
    }

    @Test
    void selectGage() {
        TimeGageDTO gage = gageService.getGage(6l);
        System.out.println(gage.getGage());
    }

}