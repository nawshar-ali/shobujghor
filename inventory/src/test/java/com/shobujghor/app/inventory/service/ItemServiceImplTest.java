package com.shobujghor.app.inventory.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shobujghor.app.inventory.repository.ItemRepository;
import com.shobujghor.app.utility.dto.ItemDto;
import com.shobujghor.app.utility.exception.ApplicationException;
import com.shobujghor.app.utility.exception.ErrorHelperService;
import com.shobujghor.app.utility.models.Item;
import com.shobujghor.app.utility.request.inventory.FetchItemRequest;
import com.shobujghor.app.utility.request.inventory.ItemByCategoryRequest;
import com.shobujghor.app.utility.response.inventory.ItemByCategoryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

    @Mock
    private ItemRepository mockItemRepository;
    @Mock
    private ErrorHelperService mockErrorHelperService;

    private ItemServiceImpl itemServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        itemServiceImplUnderTest = new ItemServiceImpl(mockItemRepository, new ObjectMapper(), mockErrorHelperService);
    }

    @Test
    void testGetItemsByCategory() {
        // Setup
        final ItemByCategoryRequest request = ItemByCategoryRequest.builder()
                .categoryName("categoryName")
                .build();
        final ItemByCategoryResponse expectedResult = ItemByCategoryResponse.builder()
                .items(List.of(ItemDto.builder().build()))
                .build();
        when(mockItemRepository.getItemListByCategory("categoryName")).thenReturn(List.of(Item.builder().build()));

        // Run the test
        final ItemByCategoryResponse result = itemServiceImplUnderTest.getItemsByCategory(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetItemsByCategory_ItemRepositoryReturnsNoItems() {
        // Setup
        final ItemByCategoryRequest request = ItemByCategoryRequest.builder()
                .categoryName("categoryName")
                .build();
        final ItemByCategoryResponse expectedResult = ItemByCategoryResponse.builder()
                .build();
        when(mockItemRepository.getItemListByCategory("categoryName")).thenReturn(Collections.emptyList());

        // Run the test
        final ItemByCategoryResponse result = itemServiceImplUnderTest.getItemsByCategory(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetItem() {
        // Setup
        final FetchItemRequest request = FetchItemRequest.builder()
                .categoryName("categoryName")
                .itemName("itemName")
                .build();
        final ItemDto expectedResult = ItemDto.builder().build();
        when(mockItemRepository.getData("categoryName", "itemName")).thenReturn(Optional.of(Item.builder().build()));

        // Run the test
        final ItemDto result = itemServiceImplUnderTest.getItem(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetItem_ItemRepositoryReturnsAbsent() {
        // Setup
        final FetchItemRequest request = FetchItemRequest.builder()
                .categoryName("categoryName")
                .itemName("itemName")
                .build();
        when(mockItemRepository.getData("categoryName", "itemName")).thenReturn(Optional.empty());

        // Configure ErrorHelperService.buildExceptionFromCode(...).
        final ApplicationException applicationException = new ApplicationException("errorCode", "message",
                HttpStatus.OK);
        when(mockErrorHelperService.buildExceptionFromCode("ITEM_NOT_FOUND")).thenReturn(applicationException);

        // Run the test
        assertThatThrownBy(() -> itemServiceImplUnderTest.getItem(request)).isInstanceOf(ApplicationException.class);
    }
}
