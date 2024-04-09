package com.shobujghor.app.gateway.api;

import com.shobujghor.app.utility.request.inventory.ItemByCategoryRequest;
import com.shobujghor.app.utility.response.inventory.CategoryListResponse;
import com.shobujghor.app.utility.response.inventory.ItemByCategoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "inventoryClient", url = "http://localhost:8003/inventory")
public interface InventoryClient {
    @GetMapping("/category/list")
    CategoryListResponse getCategories();

    @PostMapping("/item/list")
    ItemByCategoryResponse getItemsByCategory(ItemByCategoryRequest request);
}
