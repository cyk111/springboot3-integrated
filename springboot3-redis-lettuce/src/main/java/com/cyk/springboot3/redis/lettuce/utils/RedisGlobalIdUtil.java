package com.cyk.springboot3.redis.lettuce.utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author cyk
 * @date 2023/10/28 14:13
 */
public class RedisGlobalIdUtil {


    /**
     * 常见的 全局唯一id 生成策略
     * - 数据库自增长序列或字段生成id
     * - UUID
     * - Redis生成ID
     * - zookeeper生成ID
     * - Twitter的snowflake算法
     *  参考：
     *  - https://cloud.tencent.com/developer/article/1884037
     *  - https://pdai.tech/md/arch/arch-z-id.html
     */

    /**
     * 一直递增的全局id
     *
     * @param redisTemplate redis客户端对象
     * @param busId         业务id，可以按需配置
     * @param step          步长，即每次递增的间隔
     */
    public static String getNo(RedisTemplate<String, Object> redisTemplate, String busId, int step) {
        //保存redis中的key，注意不要重复
        String redisKey = "uniqueNo_";
        //利用increment即redis原生incrBy命令的原子性特性生成递增的序列号
        Long increment = redisTemplate.opsForValue().increment(redisKey, step);
        if (increment == null) {
            throw new RuntimeException("redis命令执行失败");
        }
        //业务id+递增id，如果需要纯数字，去掉业务id即可
        return busId + increment;
    }


    /**
     * 以天为间隔的递增序列号
     * @param redisTemplate redis客户端对象
     * @param busId 业务id，可以按需配置
     * @param step 步长，即每次递增的间隔
     */
    public static String getNoSpiltDay(RedisTemplate<String, Object> redisTemplate, String busId, int step) {
        //当天日期，比如20221226
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        //保存redis中的key，注意不要重复
        String redisKey = "uniqueNo_" + date;
        //利用increment即redis原生incrBy命令的原子性特性生成递增的序列号
        Long increment = redisTemplate.opsForValue().increment(redisKey, step);
        if (increment == null) {
            throw new RuntimeException("redis命令执行失败");
        }
        if (step == increment.intValue()) {
            //首次执行时，给redisKey设置ttl，第二天这个key就可以被redis自动删除
            redisTemplate.expire(redisKey, 25, TimeUnit.HOURS);
        }
        //组合  20221226 + 业务id + 0001(可以根据需要自由调整序列号的长度)
        return date + busId + String.format("%04d", increment);
    }


    /**
     * 批量获取id
     *
     * @param redisTemplate redis客户端对象
     * @param busId         业务id，可以按需配置
     * @param size          获取的id个数，与步长类似
     */
    public static List<String> getNoByGroup(RedisTemplate<String, Object> redisTemplate, String busId, int size) {
        //保存redis中的key，注意不要重复
        String redisKey = "uniqueNo_group";
        //设置步长为size，相当于一次性申请size个id
        Long increment = redisTemplate.opsForValue().increment(redisKey, size);
        if (increment == null) {
            throw new RuntimeException("redis命令执行失败");
        }
        long begin = increment - Long.parseLong(size + "");
        List<String> rs = new ArrayList<>();
        for (long i = begin + 1; i <= increment; i++) {
            rs.add(busId + i);
        }
        return rs;
    }




}
