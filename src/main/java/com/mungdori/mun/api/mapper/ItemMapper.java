package com.mungdori.mun.api.mapper;

import com.mungdori.mun.api.domain.item.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemMapper {
    void updateItemIdByUserId(@Param("userId") Long userId, @Param("itemId") Long itemId);

    void insertItem(Item item);
    Item selectItemById(Long itemId);
}
