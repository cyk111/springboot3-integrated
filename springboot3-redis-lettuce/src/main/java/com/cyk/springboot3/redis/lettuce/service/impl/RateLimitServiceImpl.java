package com.cyk.springboot3.redis.lettuce.service.impl;

import com.cyk.springboot3.redis.lettuce.service.RateLimitService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author cyk
 * @date 2023/10/28 21:03
 */
@Service
public class RateLimitServiceImpl implements RateLimitService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String,Object> startQps(Map<String,Object> paramMap) {
        //根据前端传递的qps上线
        Integer times = 100;
        if (paramMap.containsKey("times")) {
            times = Integer.valueOf(paramMap.get("times").toString());
        }
        String redisKey = "redisQps";
        RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger(redisKey, redisTemplate.getConnectionFactory());
        int no = redisAtomicInteger.getAndIncrement();
        //设置时间固定时间窗口长度 1S
        if (no == 0) {
            redisAtomicInteger.expire(1, TimeUnit.SECONDS);
        }
        //判断是否超限  time=2 表示qps=3
        if (no > times) {
            throw new RuntimeException("qps refuse request");
        }
        //返回成功告知
        Map<String,Object> map = new HashMap<>();
        map.put("success", "success");
        return map;
    }


    @Override
    public Map<String, Object> startList(Map<String, Object> paramMap) {
        String redisKey = "qpsZset";
        Integer times = 100;
        if (paramMap.containsKey("times")) {
            times = Integer.valueOf(paramMap.get("times").toString());
        }
        long currentTimeMillis = System.currentTimeMillis();
        // inter 为时间 ？
        long inter = 8; // todo 需要确定值
        long interMills = inter * 1000L;
        Long count = redisTemplate.opsForZSet().count(redisKey, currentTimeMillis - interMills, currentTimeMillis);
        if (count > times) {
            throw new RuntimeException("qps refuse request");
        }
        redisTemplate.opsForZSet().add(redisKey, UUID.randomUUID().toString(), currentTimeMillis);
        Map<String, Object> map = new HashMap<>();
        map.put("success", "success");
        return map;
    }

    @Override
    public Map<String, Object> startLoutong(Map<String, Object> paramMap) {
        String redisKey = "qpsList";
        Integer times = 100;
        if (paramMap.containsKey("times")) {
            times = Integer.valueOf(paramMap.get("times").toString());
        }
        Long size = redisTemplate.opsForList().size(redisKey);
        if (size >= times) {
            throw new RuntimeException("qps refuse request");
        }
        Long aLong = redisTemplate.opsForList().rightPush(redisKey, paramMap);
        if (aLong > times) {
            //为了防止并发场景。这里添加完成之后也要验证。  即使这样本段代码在高并发也有问题。此处演示作用
            redisTemplate.opsForList().trim(redisKey, 0, times - 1);
            throw new RuntimeException("qps refuse request");
        }
        Map <String, Object> map = new HashMap<>();
        map.put("success", "success");
        return map;
    }


    @Override
    public Map<String, Object> startLingpaitong(Map<String, Object> paramMap) {
        String redisKey = "lingpaitong";
        String token = redisTemplate.opsForList().leftPop(redisKey).toString();
        //正常情况需要验证是否合法，防止篡改
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("令牌桶拒绝");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("success", "success");
        return map;
    }

    @Scheduled(cron="*/1 * * * * ?")
    private void process(){
        String redisKey = "lingpaitong";
        //一次性生产两个
        System.out.println("正在消费。。。。。。");
        for (int i = 0; i < 2; i++) {
            redisTemplate.opsForList().rightPush(redisKey, i);
        }
    }

}
