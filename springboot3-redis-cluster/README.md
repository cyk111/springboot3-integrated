### 在docker上构建redis集群

#### 拉取镜像，新建docker容器redis实例



docker run -d --name redis-node-1 --net host --privileged=true -v /data/redis/share/redis-node-1:/data  -v  /data/redis/share/redis-node-1/config/redis.conf:/data/config/redis.conf  redis:6.0.8  redis-server /data/config/redis.conf --cluster-enabled yes  --appendonly yes --port 6381
docker run -d --name redis-node-2 --net host --privileged=true -v /data/redis/share/redis-node-2:/data  -v  /data/redis/share/redis-node-2/config/redis.conf:/data/config/redis.conf  redis:6.0.8  redis-server /data/config/redis.conf --cluster-enabled yes  --appendonly yes --port 6382
docker run -d --name redis-node-3 --net host --privileged=true -v /data/redis/share/redis-node-3:/data  -v  /data/redis/share/redis-node-3/config/redis.conf:/data/config/redis.conf  redis:6.0.8  redis-server /data/config/redis.conf --cluster-enabled yes  --appendonly yes --port 6383
docker run -d --name redis-node-4 --net host --privileged=true -v /data/redis/share/redis-node-4:/data  -v  /data/redis/share/redis-node-4/config/redis.conf:/data/config/redis.conf  redis:6.0.8  redis-server /data/config/redis.conf --cluster-enabled yes  --appendonly yes --port 6384
docker run -d --name redis-node-5 --net host --privileged=true -v /data/redis/share/redis-node-5:/data  -v  /data/redis/share/redis-node-5/config/redis.conf:/data/config/redis.conf  redis:6.0.8  redis-server /data/config/redis.conf --cluster-enabled yes  --appendonly yes --port 6385
docker run -d --name redis-node-6 --net host --privileged=true -v /data/redis/share/redis-node-6:/data  -v  /data/redis/share/redis-node-6/config/redis.conf:/data/config/redis.conf  redis:6.0.8  redis-server /data/config/redis.conf --cluster-enabled yes  --appendonly yes --port 6386


命令详解

# 创建并运行docker实例 -d 表示后台运行
docker run -d  
# 指定容器名字
--name redis-node-1
# 使用宿主机的ip和默认端口(默认)
--net host 
# 获取宿主机root用户的权限
--privileged=true
# 容器卷, 宿主机地址:docker内部地址
-v /data/redis/share/redis-node-1:/data
# 配置文件, 宿主机地址:docker内部地址
-v /data/redis/share/redis-node-1/config/redis.conf:/data/config/redis.conf
# 启动时，使用配置文件信息
redis-server /data/config/redis.conf
# redis镜像和版本号
redis:6.0.8
# 开启redis集群
--cluster-enabled yes 
# 开启持久化
--appendonly yes
# redis 端口
--port 6381


#### 进入容器构建集群关联关系

docker exec -it redis-node-1 /bin/bash
## 注意，进入docker容器后才能执行一下命令，且注意自己的真实IP地址
-- 外网
redis-cli --cluster create 101.200.157.190:6381 101.200.157.190:6382 101.200.157.190:6383 101.200.157.190:6384 101.200.157.190:6385 101.200.157.190:6386 --cluster-replicas 1
-- 内网
redis-cli --cluster create 172.19.74.134:6381 172.19.74.134:6382 172.19.74.134:6383 172.19.74.134:6384 172.19.74.134:6385 172.19.74.134:6386 --cluster-replicas 1
-- 127.0.0.1
redis-cli --cluster create 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 127.0.0.1:6385 127.0.0.1:6386 --cluster-replicas 1

## cluster-replicas 1 表示为每个master创建一个slave节点

## 进入任意一台redis机器
redis-cli -p 6381 
## 查看集群信息
cluster info 
## 查看主从信息
cluster nodes

# 5. 查看ip
docker inspect redis01 | grep IPAddress
 


- [docker-compose在linux安装]()


