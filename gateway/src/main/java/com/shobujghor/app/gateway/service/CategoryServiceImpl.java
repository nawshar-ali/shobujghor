package com.shobujghor.app.gateway.service;

import com.shobujghor.app.gateway.api.InventoryClient;
import com.shobujghor.app.utility.response.inventory.CategoryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final InventoryClient inventoryClient;

    @Override
    public CategoryListResponse getCategories() {
        return inventoryClient.getCategories();
    }
}
