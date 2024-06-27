package com.mungdori.mun.api.repository.item;

import com.mungdori.mun.api.domain.item.Item;

public interface ItemRepository {


    void updateUserItem(Long userId,Long itemId);

    Item findItem(Long itemId);
}
