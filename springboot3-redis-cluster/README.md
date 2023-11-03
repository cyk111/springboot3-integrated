### 在docker上构建redis集群

#### 拉取镜像，新建docker容器redis实例
### 创建网络
docker network create --subnet=192.168.200.0/24 redis-cluster-net

docker run -d --name redis-node-1 --net redis_cluster --ip 172.38.0.11 --privileged=true -v /Users/chenyongke/Downloads/data/redis/share/redis-node-1:/data redis:6.0.8 --cluster-enabled yes --appendonly yes --port 6381                                    
docker run -d --name redis-node-2 --net redis_cluster --ip 172.38.0.12 --privileged=true -v /Users/chenyongke/Downloads/data/redis/share/redis-node-2:/data redis:6.0.8 --cluster-enabled yes --appendonly yes --port 6382                                    
docker run -d --name redis-node-3 --net redis_cluster --ip 172.38.0.13 --privileged=true -v /Users/chenyongke/Downloads/data/redis/share/redis-node-3:/data redis:6.0.8 --cluster-enabled yes --appendonly yes --port 6383                                   
docker run -d --name redis-node-4 --net redis_cluster --ip 172.38.0.14 --privileged=true -v /Users/chenyongke/Downloads/data/redis/share/redis-node-4:/data redis:6.0.8 --cluster-enabled yes --appendonly yes --port 6384                                   
docker run -d --name redis-node-5 --net redis_cluster --ip 172.38.0.15 --privileged=true -v /Users/chenyongke/Downloads/data/redis/share/redis-node-5:/data redis:6.0.8 --cluster-enabled yes --appendonly yes --port 6385                                   
docker run -d --name redis-node-6 --net redis_cluster --ip 172.38.0.16 --privileged=true -v /Users/chenyongke/Downloads/data/redis/share/redis-node-6:/data redis:6.0.8 --cluster-enabled yes --appendonly yes --port 6386




命令详解
docker run -d  
# 创建并运行docker实例 -d 表示后台运行
--name redis-node-1
# 指定容器名字
--net host 
# 使用宿主机的ip和默认端口(默认)
--privileged=true
# 获取宿主机root用户的权限
-v /data/redis/share/redis-node-1:/data
# 容器卷, 宿主机地址:docker内部地址
redis:6.0.8
# redis镜像和版本号
--cluster-enabled yes 
# 开启redis集群
--appendonly yes
# 开启持久化
--port 6381
# redis 端口

#### 进入容器构建集群关联关系

docker exec -it redis-node-1 /bin/bash
## 注意，进入docker容器后才能执行一下命令，且注意自己的真实IP地址

redis-cli --cluster create 172.38.0.11:6381 172.38.0.11:6382 172.38.0.11:6383 172.38.0.11:6384 172.38.0.11:6385 172.38.0.11:6386 --cluster-replicas 1

redis-cli --cluster create 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 127.0.0.1:6385 127.0.0.1:6386 --cluster-replicas 1
## cluster-replicas 1 表示为每个master创建一个slave节点
redis-cli -p 6381 
## 进入任意一台redis机器
cluster info 
## 查看集群信息
cluster nodes
## 查看主从信息




-------------------------
# 新建一个 redis 的网卡，该网卡下仅部署redis服务

docker network create redis_cluster --subnet 172.38.0.0/16 --driver bridge

# 1. 拉取redis. 目前我拉取最新的是7.0.12
docker pull redis
# 2. 下载配置文件
wget https://raw.githubusercontent.com/redis/redis/7.0/redis.conf
# 3. 移到对应目录
mkdir -p /opt/docker/redis
mv redis.conf /opt/docker/redis/
# 4. 启动(3主3从)
docker run -it -d --name redis01 -v /Users/chenyongke/Downloads/docker/redis:/etc/redis -p 36379:6379 redis redis-server /etc/redis/redis.conf --net redis_cluster --ip 172.38.0.11
docker run -it -d --name redis02 -v /Users/chenyongke/Downloads/docker/redis:/etc/redis -p 36380:6379 redis redis-server /etc/redis/redis.conf --net redis_cluster --ip 172.38.0.12
docker run -it -d --name redis03 -v /Users/chenyongke/Downloads/docker/redis:/etc/redis -p 36381:6379 redis redis-server /etc/redis/redis.conf --net redis_cluster --ip 172.38.0.13
docker run -it -d --name redis04 -v /Users/chenyongke/Downloads/docker/redis:/etc/redis -p 36382:6379 redis redis-server /etc/redis/redis.conf --net redis_cluster --ip 172.38.0.14
docker run -it -d --name redis05 -v /Users/chenyongke/Downloads/docker/redis:/etc/redis -p 36383:6379 redis redis-server /etc/redis/redis.conf --net redis_cluster --ip 172.38.0.15
docker run -it -d --name redis06 -v /Users/chenyongke/Downloads/docker/redis:/etc/redis -p 36384:6379 redis redis-server /etc/redis/redis.conf --net redis_cluster --ip 172.38.0.16

# docker rm redis01 redis02 redis03

# 5. 查看ip
docker inspect redis01 | grep IPAddress
docker inspect redis02 | grep IPAddress
docker inspect redis03 | grep IPAddress
docker inspect redis04 | grep IPAddress
docker inspect redis05 | grep IPAddress
docker inspect redis06 | grep IPAddress

# docker stop redis01 redis02 redis03 redis04 redis05 redis06

# 6. 启动容器
docker start redis01 redis02 redis03 redis04 redis05 redis06

# 7. 进入redis, 并配置集群
docker exec -it redis01 /bin/bash
redis-cli --cluster create 172.17.0.2:6379 172.17.0.3:6379 172.17.0.4:6379 172.17.0.5:6379 172.17.0.6:6379 172.17.0.7:6379 --cluster-replicas 1



