package com.cyk.springboot3.redis.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cyk
 * @date 2023/10/28 09:14
 */
@SpringBootApplication
public class ClusterRedisApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClusterRedisApplication.class,args);
    }

    /**
     * 使用docker-compose 方式搭建集群成功
     * 首先 linux 系统安装  docker-compose
     * 参考链接  https://www.cnblogs.com/duzhaoqi/p/17202916.html
     *
     * 然后按照 这个链接
     * https://cloud.tencent.com/developer/article/1803792
     * 操作 即可
     *
     * 注意事项  公网服务器要开端口
     *
     * 一个是暴漏端口 6381 - 6386
     *  集群总线端口也要开
     *  集群总线端口为redis客户端连接的端口 + 10000
     *  否则 初始化集群时候
     *  redis集群Waiting for the cluster to join一直在等待
     *
     *  在执行 redis-cli --cluster create 命令时候 如果配置了密码 需要添加  -a 密码 才能执行
     *
     *  常用命令
     *  进入redis, 并配置集群
     *  docker exec -it redis01 /bin/bash
     *
     *  有密码 需要 auth 密码 后在操作
     *
     *  ## 进入任意一台redis机器
     *   cluster info
     * ## 查看集群信息
     *   cluster nodes
     * ## 查看主从信息
     *
     */

    // 如果是 不是使用 docker 搭建 redis集群，这个链接可以参考
    //https://blog.csdn.net/lingbomanbu_lyl/article/details/107999780

    // ------------

    // 使用 docker 搭建集群
    //  https://blog.csdn.net/qq_53267860/article/details/129270270


    // 官方文档
    // https://redis.io/docs/reference/cluster-spec/

}
