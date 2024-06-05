package com.shobujghor.app.inventory.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shobujghor.app.inventory.repository.dynamo.ItemRepository;
import com.shobujghor.app.utility.dto.ItemDto;
import com.shobujghor.app.utility.request.inventory.FetchItemRequest;
import com.shobujghor.app.utility.request.inventory.ItemByCategoryRequest;
import com.shobujghor.app.utility.response.inventory.ItemByCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ObjectMapper objectMapper;

    @Override
    public ItemByCategoryResponse getItemsByCategory(ItemByCategoryRequest request) {
        var itemList = itemRepository.getItemListByCategory(request.getCategoryName())
                .stream()
                .map(item -> objectMapper.convertValue(item, ItemDto.class))
                .collect(Collectors.toUnmodifiableList());

        return ItemByCategoryResponse.builder().items(itemList).build();
    }

    @Override
    public ItemDto getItem(FetchItemRequest request) {
        var item = itemRepository.getData(request.getCategoryName(), request.getItemName());
        return objectMapper.convertValue(item, ItemDto.class);
    }
}
