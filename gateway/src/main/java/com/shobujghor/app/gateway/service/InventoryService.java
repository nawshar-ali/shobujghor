package com.shobujghor.app.gateway.service;

import com.shobujghor.app.utility.request.inventory.ItemByCategoryRequest;
import com.shobujghor.app.utility.response.inventory.CategoryListResponse;
import com.shobujghor.app.utility.response.inventory.ItemByCategoryResponse;

public interface InventoryService {
    CategoryListResponse getCategories();

    ItemByCategoryResponse getItemsByCategory(ItemByCategoryRequest request);
}
