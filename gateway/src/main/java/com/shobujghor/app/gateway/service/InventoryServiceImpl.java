package com.shobujghor.app.gateway.service;

import com.shobujghor.app.gateway.api.InventoryClient;
import com.shobujghor.app.utility.request.inventory.ItemByCategoryRequest;
import com.shobujghor.app.utility.response.inventory.CategoryListResponse;
import com.shobujghor.app.utility.response.inventory.ItemByCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryClient inventoryClient;

    @Override
    public CategoryListResponse getCategories() {
        return inventoryClient.getCategories();
    }

    @Override
    public ItemByCategoryResponse getItemsByCategory(ItemByCategoryRequest request) {
        return inventoryClient.getItemsByCategory(request);
    }
}
