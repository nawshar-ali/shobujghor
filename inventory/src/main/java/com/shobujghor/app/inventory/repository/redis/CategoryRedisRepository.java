package com.shobujghor.app.inventory.repository.redis;

import com.google.gson.Gson;
import com.shobujghor.app.utility.models.Category;
import com.shobujghor.app.utility.redis.AbstractRedisRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRedisRepository extends AbstractRedisRepository<Category> {
    public CategoryRedisRepository(RedisTemplate<String, String> redisTemplate, Gson gson) {
        super(redisTemplate, Category.class, gson);
    }
}
