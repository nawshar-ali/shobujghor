package com.shobujghor.app.utility.redis;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractRedisRepository<T> {

    private final RedisTemplate<String, String> redisTemplate;
    private final Class<T> tClass;
    private final Gson gson;

    public Optional<T> getData(String cacheKey, Object dataKey) {
        var dataStr = (String) redisTemplate.opsForHash()
                .get(cacheKey, dataKey);

        return StringUtils.isEmpty(dataStr) ? Optional.empty() : Optional.of(gson.fromJson(dataStr, tClass));
    }


    public void saveData(String cacheKey, Object dataKey, Object data) {
        redisTemplate.opsForHash()
                .put(cacheKey, dataKey, gson.toJson(data));
    }

    public void saveDataWithExpiry(String cacheKey, Object data, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(cacheKey, gson.toJson(data), timeout, timeUnit);
    }

    public void updateWithExistingTTL(String cacheKey, Object data, TimeUnit timeUnit) {
        var expire = redisTemplate.getExpire(cacheKey, timeUnit);
        if (expire == null) {
            throw new NullPointerException("Redis error");
        }
        redisTemplate.opsForValue().set(cacheKey, gson.toJson(data), expire, timeUnit);
    }

    public Optional<T> getData(String cacheKey) {
        var dataStr = redisTemplate.opsForValue().get(cacheKey);

        return StringUtils.isEmpty(dataStr) ? Optional.empty() : Optional.of(gson.fromJson(dataStr, tClass));
    }

    public void saveBatchData(String cacheKey, Map<String, T> map) {

        redisTemplate.opsForHash()
                .putAll(cacheKey, map);
    }

    public void setExpiry(String globalKey, long timeout, TimeUnit timeUnit) {
        redisTemplate.expire(globalKey, timeout, timeUnit);
    }

    protected Map<String, T> getAllHashData(String cacheKey) {
        Map<Object, Object> objectResult = redisTemplate.opsForHash().entries(cacheKey);
        Map<String, T> result = new HashMap<>();
        objectResult.forEach((key, value) ->
                result.put(String.valueOf(key), gson.fromJson(String.valueOf(value), tClass)));

        return result;
    }

    public void deleteData(String cacheKey, Object dataKey) {
        redisTemplate.opsForHash().delete(cacheKey, dataKey);
    }

    protected void deleteData(String cacheKey) {
        redisTemplate.delete(cacheKey);
    }

    protected boolean hasKey(String cacheKey) {

        return redisTemplate.hasKey(cacheKey);
    }

}
