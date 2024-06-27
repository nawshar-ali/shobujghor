package com.shobujghor.app.contentmanager.service;

import com.shobujghor.app.contentmanager.api.InventoryClient;
import com.shobujghor.app.contentmanager.repository.redis.CategoryRedisRepository;
import com.shobujghor.app.utility.constants.RedisCacheKeys;
import com.shobujghor.app.utility.dto.CategoryDto;
import com.shobujghor.app.utility.response.contentmanager.HomePageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomePageServiceImpl implements HomePageService {

    private final InventoryClient inventoryClient;
    private final CategoryRedisRepository categoryRedisRepository;
    private final long CATEGORY_LIST_CACHE_TIMEOUT = 1800;

    @Override
    public HomePageResponse getHomePageData() {
        return HomePageResponse.builder()
                .categories(getCategories())
                .build();
    }

    private List<CategoryDto> getCategories() {
        var categoryListResponseOpt = categoryRedisRepository.getData(RedisCacheKeys.CATEGORY_LIST.name());

        if (categoryListResponseOpt.isPresent()) {
            log.info("Found categories in cache");
            return categoryListResponseOpt.get().getCategories();
        }

        var categoryListResponse = inventoryClient.getCategories();
        categoryRedisRepository.saveDataWithExpiry(RedisCacheKeys.CATEGORY_LIST.name(), categoryListResponse, CATEGORY_LIST_CACHE_TIMEOUT, TimeUnit.SECONDS);
        return categoryListResponse.getCategories();
    }
}
