package com.shobujghor.app.inventory.service;

import com.shobujghor.app.utility.request.inventory.ItemByCategoryRequest;
import com.shobujghor.app.utility.response.inventory.ItemByCategoryResponse;

public interface ItemService {
    ItemByCategoryResponse getItemsByCategory(ItemByCategoryRequest request);
}
