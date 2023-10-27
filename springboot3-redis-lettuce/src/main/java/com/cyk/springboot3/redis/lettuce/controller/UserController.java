package com.cyk.springboot3.redis.lettuce.controller;

import com.cyk.springboot3.redis.lettuce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author cyk
 * @date 2023/10/27 14:46
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(path = "/testRedisObject",method = RequestMethod.GET)
    public User testObj() throws Exception {
        User user = new User();
        user.setUserId(12L);
        user.setNickName("aaa");
        user.setRealName("bbb");
        user.setIsDel(1);

        // 此时只是实体类只是实现了序列化接口，使用客户端查看 key是一堆乱码，解决redis存储序列化乱码问题
        redisTemplate.opsForValue().set(user.getUserId()+"",user);
        User u = (User) redisTemplate.opsForValue().get(user.getUserId()+"");

        // 为了方便系统交互使用JSON数据格式, 需要对 RedisTemplate 进行包装
        // 配置 redisconfig  key 正常展示

        return u;
    }

    // 数据类型测试
    // string
    @RequestMapping(path = "/testRedisString",method = RequestMethod.GET)
    public void testDataType(){
        // 普通字符串
        stringRedisTemplate.opsForValue().set("test1", UUID.randomUUID().toString());
        String k = stringRedisTemplate.opsForValue().get("test1");
        System.out.println(k);

        //list
        stringRedisTemplate.opsForList().leftPush("a1","1");
        stringRedisTemplate.opsForList().leftPush("a1","2");
        stringRedisTemplate.opsForList().leftPush("a1","3");
        String a1 = stringRedisTemplate.opsForList().leftPop("a1");
        System.out.println(a1);

        //set
        stringRedisTemplate.opsForSet().add("set1","1","2","3");
        Boolean set1 = stringRedisTemplate.opsForSet().isMember("set1", "2");
        System.out.println(set1);

        //zset
        stringRedisTemplate.opsForZSet().add("zset1","张三",12);
        stringRedisTemplate.opsForZSet().add("zset1","李四",13);
        stringRedisTemplate.opsForZSet().add("zset1","王五",14);
        ZSetOperations.TypedTuple<String> zset1 = stringRedisTemplate.opsForZSet().popMax("zset1");
        System.out.println(zset1.getValue()+":"+zset1.getScore());

        //hash
        stringRedisTemplate.opsForHash().put("hash1","name","tom");
        stringRedisTemplate.opsForHash().put("hash1","age","12");
        String v = (String)stringRedisTemplate.opsForHash().get("hash1","age");
        System.out.println("v=====" + v);

    }

    // 加锁机制


}
