package com.cyk.springboot3.redis.lettuce.controller;

import com.cyk.springboot3.redis.lettuce.common.Result;
import com.cyk.springboot3.redis.lettuce.constant.RedisConstants;
import com.cyk.springboot3.redis.lettuce.entity.User;
import com.cyk.springboot3.redis.lettuce.service.UserService;
import com.cyk.springboot3.redis.lettuce.utils.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author cyk
 * @date 2023/10/28 10:42
 */
@RestController
@RequestMapping("/testCase")
public class UseCaseController {

    /**
     * 测试三种常用的缓存策略
     * 旁路策略  cache aside pattern
     *   - 平时使用比较多的策略，比较适合读请求比较多的场景，需要同时维护DB 和 Cache 并且一DB为准
     *   - 写操作  先更新数据库 然后直接删除cache
     *   - 读操作  从cache读取数据，读取到就直接返回，cache中读不到，就从DB读取返回，再把数据写到cache 中
     *   - 写操作时候，不管先删除缓存还是先删数据库，都可能存在数据不一致，只不是读操作快于写操作，所以选择先更新DB
     *   - 缺点：
     *      - 首次请求的数据不一定在cache中，第一次请求可能会很慢，请求比较多会影响系统性能，
     *          - 解决方法，将热点数据提前写入cache中
     *      - 写操作如果比较频繁，导致cache数据频繁删除，会影响缓存命中率
     *          - 数据库和缓存强一直场景：更新DB的时候同样更新cache，不过需要加一个锁/分布式锁来保证更新cache的时候不存在线程安全问题
     *          - 可以短暂的允许数据库和缓存数据不一致的场景：更新DB的时候同样更新cache，但是给缓存加一个比较短的过期时间，这样的话就可以保证即使数据不一致的话影响也比较小
     * 读写穿透策略
     *   - 读写穿透中把服务端视为主要存储器，从中读取数据并将数据写入其中，cache前置，cache服务负责将此数据读取和写入DB，从而减轻应用程序的职责
     *   - 写操作  先查cache，cache中不存在，直接更新DB，cache中存在，则先更新cache，然后cache服务自己更新DB（同时更新DB和cache）
     *   - 读操作  先从cache中读取数据，读取到直接返回，从cache中读取不到，则先从DB加载写入到cache后返回响应
     *   - 问题    读写穿透也存在首次请求数据一定不在cache中的问题，对于热点数据可以提前写入缓存中
     *   - 需要缓存系统高可用，如果出现故障，则系统崩溃
     * 异步缓存策略
     *   - 读写穿透很相似，两者最大的不同点就是：读写穿透是同步更新DB和cache，而异步缓存写入则是只更新cache，不直接更新DB，而是改为异步批量的方式更新DB
     *   - 这种方式对数据一致性带来了更大的挑战，比如cache数据可能还没异步更新DB，cache服务可能就挂了
     *   - 这种策略在我们平时开发过程中也非常少见，但是不代表它的应用场景少，比如消息队列中消息的异步写入磁盘、MySQL的InnoDB Buffer Pool机制都用到了这种策略
     *   - 异步缓存写入的写性能非常高，非常适合写数据经常变化又对数据一致性要求没那么高的场景下使用，比如浏览量、点赞量等
     */


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;


    // 使用旁路模式实现
    // 读操作
    @RequestMapping(path = "/getUserById",method = RequestMethod.GET)
    public Result<User> getUserById(@RequestParam Long id){
        // 读操作，先从缓存中取，如果没有，查询DB,然后返回到cache 返回
        User user = (User) redisUtil.get(RedisConstants.REDIS_KEY_PREFIX_USER + id);
        if(Objects.nonNull(user)){
            return Result.success(user);
        }else {
            // 从数据库查询
            User userFromDB = userService.getUserById(id);
            if(Objects.nonNull(userFromDB)){
                redisUtil.set(RedisConstants.REDIS_KEY_PREFIX_USER + id,user);
            }
        }
        return Result.success(user);
    }

    // 写操作
    @RequestMapping(path = "/save",method = RequestMethod.POST)
    public Result<String> save(@RequestBody User user){
         // 注意先后关系
        // 先更新数据库 如果是添加,同时在缓存添加
        userService.save(user);
        return Result.success("添加成功");
    }

    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public Result<String> update(@RequestBody User user){
        // 注意先后关系
        // 如果是修改, 先更新DB,然后更新缓存
        User userDB = userService.getUserById(user.getUserId());
        if(Objects.nonNull(userDB)){
            BeanUtils.copyProperties(user,userDB);
        }
        userService.update(userDB);
        return Result.success("修改陈功");
    }


    @RequestMapping(path = "/delete",method = RequestMethod.GET)
    public Result<String> delete(@RequestParam Long id){
        // 注意先后关系
        // 如果是删除,则直接删除cache
        User userDB = userService.getUserById(id);
        if(Objects.nonNull(userDB)){
            userService.delete(userDB.getUserId());
        }
        return Result.success("删除成功");
    }



}
