package com.shobujghor.app.contentmanager.repository.redis;

import com.google.gson.Gson;
import com.shobujghor.app.utility.models.Category;
import com.shobujghor.app.utility.redis.AbstractRedisRepository;
import com.shobujghor.app.utility.response.inventory.CategoryListResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRedisRepository extends AbstractRedisRepository<CategoryListResponse> {
    public CategoryRedisRepository(RedisTemplate<String, String> redisTemplate, Gson gson) {
        super(redisTemplate, CategoryListResponse.class, gson);
    }
}
