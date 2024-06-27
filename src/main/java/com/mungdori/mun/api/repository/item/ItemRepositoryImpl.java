package com.mungdori.mun.api.repository.item;

import com.mungdori.mun.api.domain.item.Item;
import com.mungdori.mun.api.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository{

    private final ItemMapper itemMapper;
    @Override
    public void updateUserItem(Long userId, Long itemId) {
        itemMapper.updateItemIdByUserId(userId,itemId);
    }

    @Override
    public Item findItem(Long itemId) {
        return itemMapper.selectItemById(itemId);
    }
}
