package com.cyk.springboot3.redis.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cyk
 * @date 2023/10/28 09:09
 */
@SpringBootApplication
public class SentinelRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelRedisApplication.class,args);
    }

    /***
     *
     * 主从模式:
     * - 优点：
     *   master能自动将数据同步到slave，可以进行读写分离，分担master的读压力
     *   master、slave之间的同步是以非阻塞的方式进行的，同步期间，客户端仍然可以提交查询或更新请求
     * - 缺点：
     *   不具备自动容错与恢复功能，master或slave的宕机都可能导致客户端请求失败，需要等待机器重启或手动切换客户端IP才能恢复
     *   master宕机，如果宕机前数据没有同步完，则切换IP后会存在数据不一致的问题
     *   难以支持在线扩容，Redis的容量受限于单机配置
     *
     * 哨兵模式:
     *  - 有点:
     *    哨兵模式基于主从复制模式，所以主从复制模式有的优点，哨兵模式也有
     *    哨兵模式下，master挂掉可以自动进行切换，系统可用性更高
     *  - 缺点：
     *    同样也继承了主从模式难以在线扩容的缺点，Redis的容量受限于单机配置
     *    需要额外的资源来启动sentinel进程，实现相对复杂一点，同时slave节点作为备份节点不提供服务

     *   参考文档：
     *   https://www.cnblogs.com/tanghaorong/p/14337982.html
     *   https://juejin.cn/post/7206226905309429818
     *   全量配置信息
     *   https://zhuanlan.zhihu.com/p/146465201
     *   - 主要参考 哨兵搭建
     *   https://juejin.cn/post/6844903908398071815
     *
     *   - docker-compose 搭建哨兵 未实践
     *
     *   https://www.cnblogs.com/coolxin1024/p/17182557.html
     *
     */

}
