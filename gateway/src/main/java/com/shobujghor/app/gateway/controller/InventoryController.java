package com.shobujghor.app.gateway.controller;

import com.shobujghor.app.gateway.service.InventoryService;
import com.shobujghor.app.utility.request.inventory.ItemByCategoryRequest;
import com.shobujghor.app.utility.response.inventory.CategoryListResponse;
import com.shobujghor.app.utility.response.inventory.ItemByCategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/category/list")
    public CategoryListResponse getCategories() {
        return inventoryService.getCategories();
    }

    @PostMapping("/item/list")
    ItemByCategoryResponse getItemsByCategory(@RequestBody @Valid ItemByCategoryRequest request) {
        return inventoryService.getItemsByCategory(request);
    }
}
