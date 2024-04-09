package com.shobujghor.app.inventory.controller;

import com.shobujghor.app.inventory.service.ItemService;
import com.shobujghor.app.utility.dto.ItemDto;
import com.shobujghor.app.utility.request.inventory.FetchItemRequest;
import com.shobujghor.app.utility.request.inventory.ItemByCategoryRequest;
import com.shobujghor.app.utility.response.inventory.ItemByCategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/list")
    public ItemByCategoryResponse getItemsByCategory(@RequestBody @Valid ItemByCategoryRequest request) {
        return itemService.getItemsByCategory(request);
    }

    @PostMapping("/get")
    public ItemDto getItem(@RequestBody @Valid FetchItemRequest request) {
        return itemService.getItem(request);
    }
}
