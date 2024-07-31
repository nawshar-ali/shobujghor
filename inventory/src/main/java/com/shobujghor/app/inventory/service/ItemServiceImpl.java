package com.shobujghor.app.inventory.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shobujghor.app.utility.constants.ErrorUtil;
import com.shobujghor.app.inventory.repository.dynamo.ItemRepository;
import com.shobujghor.app.utility.dto.ItemDto;
import com.shobujghor.app.utility.exception.ErrorHelperService;
import com.shobujghor.app.utility.request.inventory.FetchItemRequest;
import com.shobujghor.app.utility.request.inventory.ItemByCategoryRequest;
import com.shobujghor.app.utility.response.inventory.ItemByCategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ObjectMapper objectMapper;
    private final ErrorHelperService errorHelperService;

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
        var itemOpt = itemRepository.getData(request.getCategoryName(), request.getItemName());

        if (itemOpt.isEmpty()) {
            log.error("Item: {} is not found in DB", request.getItemName());
            throw errorHelperService.buildExceptionFromCode(ErrorUtil.ITEM_NOT_FOUND);
        }

        var item = itemOpt.get();

        return objectMapper.convertValue(item, ItemDto.class);
    }
}
