package com.cyk.springboot3.redis.lettuce.controller;

import com.alibaba.fastjson.JSONObject;
import com.cyk.springboot3.redis.lettuce.common.Result;
import com.cyk.springboot3.redis.lettuce.constant.RedisConstants;
import com.cyk.springboot3.redis.lettuce.entity.Shop;
import com.cyk.springboot3.redis.lettuce.utils.RedisDistributedLock;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author cyk
 * @date 2023/10/30 20:36
 */
@RestController
public class RedisSolutionController {

    // todo  切面AOP方式实现缓存


    /**
     * 缓存穿透：客户端请求的数据在缓存和数据库都不存在，这样请求就会先访问缓存在到数据库，利用这个漏洞，一直请求缓存和数据库都不存在的数据，可能导致数据库崩溃
     * 解决方案：
     * - 1.缓存空对象：请求发送福过来缓存和数据库都没有的数据,将请求的结果设置为 null并设置为过期时间存入缓存，然后返回401状态码,等下一次请求时，直接返回null 即可，
     *     但是这样会造成内存额外的损耗，使用过期时间可以降低该缺点
     * - 2.在缓存之前使用**布隆过滤器**，布隆过滤器是由byte数组和一系列哈希函数两部分组成的数据结构, 将数据使用hash函数计算出hash值，
     *     布隆过滤器的优点是不用频繁添加缓存内存占用小，但是实现复杂，而且会出现误判,布隆过滤器判断不存在的一定不存在，判断存在的不一定存在
     */

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisDistributedLock redisDistributedLock;

    /**
     * 根据id查询商铺信息，涉及到redis的缓存
     * @param id  商铺id
     * @return 前端返回信息
     */
    @RequestMapping(path = "/queryById")
    public Result queryById(Long id) {
        // 从redis查询商铺缓存
        String shopJson = stringRedisTemplate.opsForValue().get(RedisConstants.CACHE_SHOP_KEY + id);
        // 判断该商铺缓存中是否存在
        if (StringUtils.isNotBlank(shopJson)) {
            // 存在直接返回
            Shop shop = JSONObject.parseObject(shopJson, Shop.class);
            return Result.success(shop);
        } else if ("".equals(shopJson)) {
            // 缓存中存在但是结果为""空字符串 也就是说之前使用 缓存空对象 方案时存入的，这样的话就刷新它的缓存时间返回异常
            stringRedisTemplate.expire(RedisConstants.CACHE_SHOP_KEY + id, RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
            return Result.failure(401,"店铺不存在");
        }
        // 不存在查询数据库
        Shop shop = getById(id);
        if (shop == null) {
            // 数据库中不存在 将null存入缓存设置过期时间2min并返回错误信息
            stringRedisTemplate.opsForValue().set(RedisConstants.CACHE_SHOP_KEY + id, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
            return Result.failure(500,"店铺不存在");
        }
        // 数据库中存在写入redis
        stringRedisTemplate.opsForValue().set(RedisConstants.CACHE_SHOP_KEY + id, JSONObject.toJSONString(shop), RedisConstants.CACHE_SHOP_TTL, TimeUnit.MINUTES);
        // 返回
        return Result.success(shop);
    }

    private Shop getById(Long id){
        // 从数据库查询
        // mock
        // 数据库不存在
        Shop shop = new Shop();
        return null;
    }

    /**
     *  缓存雪崩
     *  缓存雪崩是指在同一时段大量的缓存key同时失效或者Redis服务宕机，导致大量请求到达数据库，因此会给数据库带来巨大压力,
     *  为了解决大量缓存数据同时失效，可以将数据的TTL设置为随机值，而不是使用一个固定值。为了解决Redis服务宕机，
     * 可以使用主从架构的集群提高服务的可用性，万一出现宕机可以使用从节点顶上。为了进一步防止缓存雪崩，我们还可以给缓存业务添加降级限流策略，
     * 也就是说当redis发生故障的时候可以直接拒绝服务而不是继续访问数据库；或者给业务添加多级缓存，在浏览器、nginx、redis、jvm、数据库等一层层的添加缓存
     */


    /***
     *  缓存击穿:
     *
     *  缓存击穿也叫热点Key问题，就是一个被高并发访问并且缓存重建业务较复杂的key突然失效了，此时很多的请求会在瞬间给数据库带来巨大冲击
     *
     *  为了解决缓存击穿可以使用互斥锁，如果发生缓存击穿后，第一个请求查询数据库中该数据的时候，使用一个锁锁住，后续的所有请求在锁未放开之前访问这个数据就让它休眠一会重新查询缓存。
     *  这个方案优点就不说了，缺点就是在第一个线程写缓存期间，其他访问该数据的线程拿不到锁就只能处于等待状态，所以说这就很损耗性能
     *
     *  还有一种方案就是逻辑过期，顾名思义逻辑过期就是不作真正的删除，而是使用一个字段存储过期时间代替TTL的过期删除，
     *  所有的线程在获取到数据的时候都去通过过期时间字段判断是否过期，过期的话就新建一个线程先更新数据库再删除缓存，
     *  自己就返回已过期的数据，在此期间所有的访问都会返回过期数据，等到新建线程的任务完成之后再次访问的线程就负责添加新的缓存数据并返回新的数据
     *
     */

    /**
     *  互斥锁 解决 缓存击穿
     * @param id
     * @return
     */
    public Shop queryWithMutex(Long id) {
        // 从redis查询商铺缓存
        String shopJson = stringRedisTemplate.opsForValue().get(RedisConstants.CACHE_SHOP_KEY + id);
        // 判断该商铺缓存中是否存在
        if (StringUtils.isNotBlank(shopJson)) {
            // 存在直接返回
            Shop shop = JSONObject.parseObject(shopJson, Shop.class);
            return shop;
        } else if ("".equals(shopJson)) {
            // 缓存中存在但是结果为""空字符串 也就是说之前使用 缓存空对象 方案时存入的，这样的话就刷新它的缓存时间返回异常
            stringRedisTemplate.expire(RedisConstants.CACHE_SHOP_KEY + id, RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
            return null;
        }
        // 不存在先查看是否可以获取到锁，如果可以就休眠重试，否则就查询数据库写缓存
        Shop shop = null;
        try {
            if (!redisDistributedLock.lock(RedisConstants.LOCK_SHOP_KEY + id,1000,3,3)) {
                // 获取锁失败
                Thread.sleep(50);
                queryWithMutex(id);
            }
            shop = getById(id);
            if (shop == null) {
                // 数据库中不存在 将null存入缓存设置过期时间2min并返回错误信息
                stringRedisTemplate.opsForValue().set(RedisConstants.CACHE_SHOP_KEY + id, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
                return null;
            }
            // 数据库中存在写入redis
            stringRedisTemplate.opsForValue().set(RedisConstants.CACHE_SHOP_KEY + id, JSONObject.toJSONString(shop), RedisConstants.CACHE_SHOP_TTL, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放互斥锁
            redisDistributedLock.unlock(RedisConstants.LOCK_SHOP_KEY + id);
        }
        // 返回
        return shop;
    }


    // 创建线程池 用于更新缓存时间过期的时候 更新缓存使用
    private static final ExecutorService CACH_REBUILD_EXECUTOR = Executors.newFixedThreadPool(5);

    /**
     * 查询商铺信息 逻辑过期解决缓存击穿
     * @param id  商铺编号
     * @return 查询到的商铺信息
     */
   /* public Shop queryWithLogicalExpire(Long id) {
        // 从redis查询商铺缓存
        String shopJson = stringRedisTemplate.opsForValue().get(RedisConstants.CACHE_SHOP_KEY + id);
        // 判断该商铺缓存中是否存在
        if (StringUtils.isBlank(shopJson)) {
            // 不存在直接返回null
            return null;
        }
        // 存在 反序列化获取 店铺信息 和 expire字段
        RedisData redisData = JSONObject.parseObject(shopJson, RedisData.class);
        Shop shop = JSONObject.parseObject(redisData.toString(), Shop.class);
        //LocalDateTime expireTime = redisData.getExpireTime();
        // 判断是否过期 过期时间在当前时间之后即为未过期
        if (expireTime.isAfter(LocalDateTime.now())) {
            // 未过期直接返回店铺信息
            return shop;
        }

        // 已过期 尝试获取锁
        if (tryLock(RedisConstants.LOCK_SHOP_KEY + id)) {
            // 获取锁成功 开启独立线程去做缓存重建
            CACH_REBUILD_EXECUTOR.submit(() -> {
                // 创建缓存
                try {
                    saveShopWithExpireTimeToRedis(id, 20L);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    // 释放锁
                    unLock(RedisConstants.LOCK_SHOP_KEY + id);
                }
            });
        }
        // 返回
        return shop;
    }*/




}
