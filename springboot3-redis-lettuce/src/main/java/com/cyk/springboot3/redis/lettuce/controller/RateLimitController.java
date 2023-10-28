package com.cyk.springboot3.redis.lettuce.controller;

import com.cyk.springboot3.redis.lettuce.service.RateLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author cyk
 * @date 2023/10/28 20:29
 */
@RestController("/rateLimit")
public class RateLimitController {

    /***
     * 基于redis 实现四种常见的限流策略
     * - 固定时间窗口算法(计数器算法)
     *  - 使用计数器在周期内累加访问次数，当达到设定的限流值时，出发限流策略，下一周期开始，进行清零重新计数
     *  - 使用redis的incr 原子自增性和线程安全可以实现
     *  - 缺点: 有临界问题，不能平滑限流
     * - 滑动时间窗口算法
     *  - 将时间周期分为N个小周期，分别记录每个小周期内访问次数，并根据时间滑动删除过期小周期
     *  - 滑动窗口划分越多，窗口滚动就越平滑，限流的统计就会越精确
     * - 漏桶算法
     *  - 访问的请求到达时直接放入漏桶，如果当前容量达到上限，则进行丢弃（触发限流策略），漏桶以固定的速率进行释放访问请求，直到漏桶为空
     * - 令牌桶算法
     *  - 令牌桶算法是程序以r（r=时间周期/限流值）的速度向令牌桶中增加令牌，直到令牌桶满，请求到达时向令牌桶请求令牌，如获取到令牌则通过请求，否则触发限流策略
     *
     *  - 漏桶 vs 令牌桶
     *    - 漏桶：如果一下子来了很多请求，但是请求会被放在池子里面，然后以固定的速率去处理请求。
     *    - 令牌桶：以固定的速率往桶内放入令牌，一下来很多请求，只要桶内的令牌足够多，请求就会立马被处理，这就是允许突发大量请求进来。
     *    - 漏桶是请求进入桶内，但是处理请求的速率是固定的，令牌桶是只要拿到令牌请求立马会被处理。
     *    - 漏桶算法与令牌桶算法的区别在于，漏桶算法能够强行限制数据的传输速率，令牌桶算法能够在限制数据的平均传输速率的同时还允许某种程度的突发传输
     *   代码参考 https://www.cnblogs.com/zhangxinhua/p/14893185.html  https://developer.aliyun.com/article/1004834
     *   压测工具  https://help.aliyun.com/document_detail/434264.html
     */

    @Autowired
    private RateLimitService rateLimitService;
    //固定窗口
    @RequestMapping(value = "/start",method = RequestMethod.GET)
    public Map<String,Object> start(@RequestParam Map<String, Object> paramMap) {
        return rateLimitService.startQps(paramMap);
    }

    //滑动窗口
    @RequestMapping(value = "/startList",method = RequestMethod.GET)
    public Map<String,Object> startList(@RequestParam Map<String,Object> paramMap) {
        return rateLimitService.startList(paramMap);
    }

    // 漏桶
    @RequestMapping(value = "/startLoutong",method = RequestMethod.GET)
    public Map<String,Object> startLoutong(@RequestParam Map<String,Object> paramMap) {
        return rateLimitService.startLoutong(paramMap);
    }


    @RequestMapping(value = "/startLingpaitong",method = RequestMethod.GET)
    public Map<String,Object> startLingpaitong(@RequestParam Map<String,Object> paramMap) {
        return rateLimitService.startLingpaitong(paramMap);
    }

}
