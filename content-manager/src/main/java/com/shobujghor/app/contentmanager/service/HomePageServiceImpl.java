package com.shobujghor.app.contentmanager.service;

import com.shobujghor.app.contentmanager.api.InventoryClient;
import com.shobujghor.app.utility.response.contentmanager.HomePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomePageServiceImpl implements HomePageService {

    private final InventoryClient inventoryClient;

    @Override
    public HomePageResponse getHomePageData() {
        var categoryListResponse = inventoryClient.getCategories();
        return HomePageResponse.builder()
                .categories(categoryListResponse.getCategories())
                .build();
    }
}
