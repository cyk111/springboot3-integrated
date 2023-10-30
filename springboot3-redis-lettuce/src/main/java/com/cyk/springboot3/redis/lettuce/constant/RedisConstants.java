package com.cyk.springboot3.redis.lettuce.constant;

/**
 * @author cyk
 * @date 2023/10/28 11:26
 */
public class RedisConstants {


    public static final String REDIS_KEY_PREFIX_USER = "redis:user:";
    public static final String CACHE_SHOP_KEY = "redis:cache_shop_key:";

    public static final String LOCK_SHOP_KEY = "redis:lock_shop_key:";

    public static final Long CACHE_NULL_TTL = 5L;
    public static final Long CACHE_SHOP_TTL = 5L;

}
