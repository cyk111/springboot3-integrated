package com.cyk.springboot3.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cyk
 * @date 2023/10/31 16:03
 */
@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class,args);
    }

    /***
     * 参考文档：
     *
     *    https://choudalao.com/article/315
     *    https://www.jianshu.com/p/11868c76a802
     *    https://www.lixueduan.com/posts/kafka/01-install/
     *    https://juejin.cn/post/7091842457318473764
     *    kafka-map 图形化管理工具
     *    https://blog.csdn.net/y393016244/article/details/126405864
     *
     *    启动kafka镜像生成容器
     *      docker run -d --name kafka -p 9002:9002
     *      -e KAFKA_BROKER_ID=0 -e KAFKA_ZOOKEEPER_CONNECT=192.168.124.28:2181/kafka
     *      -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.124.28:9002
     *      -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9002
     *      -v /etc/localtime:/etc/localtime wurstmeister/kafka:2.12-2.5.0
     *
     *      docker run -d --restart=always
     *      --log-driver json-file
     *      --log-opt max-size=100m
     *      --log-opt max-file=2
     *      --name kafka -p 9002:9002
     *      -e KAFKA_BROKER_ID=0 -e KAFKA_ZOOKEEPER_CONNECT=172.16.152.136:2181/kafka
     *      -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://172.16.152.136:9002
     *      -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9002
     *      -v /etc/localtime:/etc/localtime wurstmeister/kafka:2.12-2.5.0
     *      参数说明：
     *      -e KAFKA_BROKER_ID=0  在kafka集群中，每个kafka都有一个BROKER_ID来区分自己
     *
     *      -e KAFKA_ZOOKEEPER_CONNECT=192.168.124.28:2181/kafka
     *      配置zookeeper管理kafka的路径192.168.124.28:2181/kafka
     *
     *      -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.124.28:9002
     *      把kafka的地址端口注册给zookeeper，如果是远程访问要改成外网IP,类如Java程序访问出现无法连接。
     *
     *      -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9002 配置kafka的监听端口
     *
     *      -v /etc/localtime:/etc/localtime 容器时间同步虚拟机的时间
     *
 *         链接：https://juejin.cn/post/6960820341631352868
     *
     *
     *
     *
     */
}
