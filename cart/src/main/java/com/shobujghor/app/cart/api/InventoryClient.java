package com.shobujghor.app.cart.api;

import com.shobujghor.app.utility.dto.ItemDto;
import com.shobujghor.app.utility.request.inventory.FetchItemRequest;
import com.shobujghor.app.utility.request.inventory.ItemByCategoryRequest;
import com.shobujghor.app.utility.response.inventory.CategoryListResponse;
import com.shobujghor.app.utility.response.inventory.ItemByCategoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "inventoryClient", url = "http://localhost:8003/inventory")
public interface InventoryClient {
    @PostMapping("/item/get")
    ItemDto getItem(FetchItemRequest request);
}
