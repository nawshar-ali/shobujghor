package com.shobujghor.app.gateway.controller;

import com.shobujghor.app.gateway.service.CategoryService;
import com.shobujghor.app.utility.response.inventory.CategoryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    public CategoryListResponse getCategories() {
        return categoryService.getCategories();
    }
}
