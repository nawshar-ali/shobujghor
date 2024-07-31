package com.shobujghor.app.inventory.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shobujghor.app.inventory.repository.CategoryRepository;
import com.shobujghor.app.utility.dto.CategoryDto;
import com.shobujghor.app.utility.models.Category;
import com.shobujghor.app.utility.response.inventory.CategoryListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class CategoryServiceImplTest {

    @Mock
    private CategoryRepository mockCategoryRepository;

    private CategoryServiceImpl categoryServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        categoryServiceImplUnderTest = new CategoryServiceImpl(mockCategoryRepository, new ObjectMapper());
    }

    @Test
    void testGetCategories() {
        // Setup
        final CategoryListResponse expectedResult = CategoryListResponse.builder()
                .categories(List.of(CategoryDto.builder().build()))
                .build();
        when(mockCategoryRepository.getListFromPaginatedScan(Category.class)).thenReturn(
                List.of(Category.builder().build()));

        // Run the test
        final CategoryListResponse result = categoryServiceImplUnderTest.getCategories();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetCategories_CategoryRepositoryReturnsNoItems() {
        // Setup
        final CategoryListResponse expectedResult = CategoryListResponse.builder()
                .build();
        when(mockCategoryRepository.getListFromPaginatedScan(Category.class)).thenReturn(Collections.emptyList());

        // Run the test
        final CategoryListResponse result = categoryServiceImplUnderTest.getCategories();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
