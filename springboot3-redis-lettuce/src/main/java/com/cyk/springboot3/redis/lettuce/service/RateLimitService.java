package com.cyk.springboot3.redis.lettuce.service;

import java.util.Map;

/**
 * @author cyk
 * @date 2023/10/28 21:03
 */
public interface RateLimitService {

    Map<String,Object> startQps(Map<String,Object> paramMap);

    Map<String,Object> startList(Map<String,Object> paramMap);

    Map<String,Object> startLoutong(Map<String,Object> paramMap);

    Map<String,Object> startLingpaitong(Map<String,Object> paramMap);
}
