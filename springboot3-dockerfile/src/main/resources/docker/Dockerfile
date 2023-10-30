# 基础镜像：仓库是java，tag是17
FROM jdk17
# 容器对外暴露8080端口,即可以通过这个端口访问容器内的服务，这个端口与容器内服务的端口存在一个对应关系，
# 也可以在docker run时指定，本文即是这么做的，所以这里注释掉了，见后面docker run命令
EXPOSE 8080

# 将本地/tmp目录持久化到Docker宿主主机内. 因为Spring Boot使用内嵌Tomcat容器,其默认使用/tmp作为工作目录。
# 所以效果就是将tomcat工作目录/tmp下的文件上传到远程docker主机的/var/lib/docker/**/
# 查在远程docker主机的文件：/var/lib/docker/overlay2/5e3d14ca7fedf5ee2c6ca2744d4da664f09f12affec8ed391740a8b92896f52f/diff/app.jar
VOLUME /tmp

#将打包好的程序拷贝到容器中的指定位置
ADD springboot3-dockerfile-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""

#容器启动后需要执行的命令
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]