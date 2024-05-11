package com.shobujghor.app.inventory.service;

import com.shobujghor.app.utility.dto.ItemDto;
import com.shobujghor.app.utility.request.inventory.FetchItemRequest;
import com.shobujghor.app.utility.request.inventory.ItemByCategoryRequest;
import com.shobujghor.app.utility.response.inventory.ItemByCategoryResponse;

public interface ItemService {
    ItemByCategoryResponse getItemsByCategory(ItemByCategoryRequest request);

    ItemDto getItem(FetchItemRequest request);
}
