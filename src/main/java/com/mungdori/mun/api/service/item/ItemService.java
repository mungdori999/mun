package com.mungdori.mun.api.service.item;

import com.mungdori.mun.api.domain.item.Item;
import com.mungdori.mun.api.domain.item.ItemDTO;
import com.mungdori.mun.api.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long setStartingItem(Long userId) {
        Long itemId = 1l;
        itemRepository.updateUserItem(userId,itemId);
        return itemId;
    }

    public ItemDTO getItem(Long itemId) {
        Item item = itemRepository.findItem(itemId);
        return new ItemDTO(item.getItemName(),item.getGltf());
    }
}
