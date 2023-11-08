### 单机模式搭建
- docker 安装redis 命令
docker run -itd --name redis-test -p 6379:6379 redis

- 使用压缩包安装，解压配置相关信息


### 主从模式搭建,配置读写分离
- 创建网络
- docker network ls
- docker network create --driver=bridge --subnet=192.168.0.0/16 redis-network

- 创建配置文件，配置信息
    ```text
        bind 0.0.0.0 //修改为允许所有的ip都能访问，默认是：127.0.0.1 因为我们要主节点和从节点不是同一个ip。
        proctected-mode no //默认yes，开启保护模式，这里便于测试不设密码，生产就必须为yes。
        daemonize no //默认no，改为yes意为以守护进程方式启动，可后台运行，除非kill进程（可选），改为yes会使以配置文件方式启动redis失败。
        dir ./ //输入本地redis数据库存放文件夹（可选）。
        appendonly yes //redis持久化（可选）。
    ```
- docker 搭建主从命令
- master主节点
docker run -d --name redis-master -p 7379:7379 -v /data/master/conf/redis.conf:/etc/redis/redis.conf -v /data/master/redis/data/:/data redis:6.0.8 redis-server /etc/redis/redis.conf --appendonly yes
- 从节点
docker run -d --name redis-slave1 -p 7380:7379 -v /data/slave1/conf/redis.conf:/etc/redis/redis.conf -v /data/slave1/redis/data/:/data redis:6.0.8 redis-server /etc/redis/redis.conf --appendonly yes
docker run -d --name redis-slave2 -p 7381:7379 -v /data/slave2/conf/redis.conf:/etc/redis/redis.conf -v /data/slave2/redis/data/:/data redis:6.0.8 redis-server /etc/redis/redis.conf --appendonly yes

# 容器名
--name redis-master
# 自定义网络
--net redis-network
# ip地址。
--ip 172.50.0.2
# 端口映射 
-p 7379:7379
# 把宿主机配置好的redis.conf放到容器内部的这个位置中 [宿主机:docker主机]
-v /data/master/conf/redis.conf:/etc/redis/redis.conf
# 把redis持久化的数据在宿主机内显示，做数据备份 [宿主机:docker主机]
-v /data/master/redis/data/:/data
# redis镜像名
redis:6.0.8
# 让redis不是无配置启动，而是按照容器内部的这个redis.conf的配置启动。
redis-server /etc/redis/redis.conf
# redis启动后数据持久化
–-appendonly yes

- 执行命令
docker exec -it redis-slave1 /bin/bash
- 配置主从
slaveof 172.17.0.5 7379
info replication




- 主从配置
- 从服务器执行命令
slaveof [master-ip] [master-port]

#### 哨兵模式搭建