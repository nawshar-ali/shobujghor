package com.shobujghor.app.inventory.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shobujghor.app.inventory.repository.dynamo.CategoryRepository;
import com.shobujghor.app.utility.dto.CategoryDto;
import com.shobujghor.app.utility.models.Category;
import com.shobujghor.app.utility.response.inventory.CategoryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ObjectMapper objectMapper;

    @Override
    public CategoryListResponse getCategories() {
        var categoryList = categoryRepository.getListFromPaginatedScan(Category.class).stream()
                .filter(Category::isEnabled)
                .map(category -> objectMapper.convertValue(category, CategoryDto.class))
                .collect(Collectors.toUnmodifiableList());

        return CategoryListResponse.builder().categories(categoryList).build();
    }
}
