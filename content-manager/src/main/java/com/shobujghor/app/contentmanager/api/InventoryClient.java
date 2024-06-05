package com.shobujghor.app.contentmanager.api;

import com.shobujghor.app.utility.response.inventory.CategoryListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "inventoryClient", url = "http://localhost:8003/inventory")
public interface InventoryClient {
    @GetMapping("/category/list")
    CategoryListResponse getCategories();
}
