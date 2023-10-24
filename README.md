# springboot3-use
 Summarize the knowledge points used in daily development of springboot3.X


### 1. springboot helloworld 需要引入
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>RELEASE</version>
            <scope>compile</scope>
        </dependency>
```
### 2 springboot 项目支持热部署 并配置相应信息
- 项目分层
- 不同参数请求 post get
- // 数据校验
- 统一返回结构体封装
- //支持热部署
- //swagger2 封装
- 
```xml
       <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional> <!-- 可以防止将devtools依赖传递到其他模块中 -->
        </dependency>
```

### 4. 项目分层、统一接口封装、参数校验、异常处理、多版本接口提供
### 5. springboot 集成swagger、集成 smartDoc
### 6. springboot 访问外部接口的几种方式
### 7. springboot 如何保障接口幂等性
### 8. springboot 实现接口限流(单实例、分布式)
### 9. springboot 集成mysql 基于JPA的封装 (oracle PostgreSQL)
### 10. springboot 集成mysql mybatis(xml、注解方式)
### 11. springboot 集成mysql 分页插件 pageHelper
### 12. springboot 集成mysql 多数据源配置
### 13. springboot 集成mysql - mybatis-plus 代码自动生成、基于字段隔离多租户
### 14. springboot 集成shardJDBC
### 15. springboot 集成数据库连接池 DBCP、C3P0、HikariCP (对比不同数据库连接池的区别)
### 16. springboot 集成数据库脚本管理工具 flyway liquibase
### 17. springboot 集成redis(jedis三种连接方式(单机、分片、集群)) / RedisTemplate、Jedis、lettuce、Redission 区分
### 18. springboot 集成分布式锁
### 19. springboot 集成 MongoDB
### 20. springboot 集成ES、kafka、rocketMQ
### 21. springboot 集成websocket  netty
### 22. springboot 集成Schedule ExecutorService
### 23. springboot 集成定时任务  quart、quartz cluster、elastic-job、xxl-job
### 24. springboot 集成文件上传下载、断点续传
### 25. springboot 集成文件 POI、EasyPOI 导入导出 Excel world
### 26. springboot 集成导出PDF
### 27. springboot 异步编程 @Async  Reactive
### 28. springboot 应用部署(jar、war  tomcat(jetty,undertow)) 、docker、 docker compose 
### 29. springboot 集成监控 actuator、springboot admin、Prometheus + grafana 



### 3 springboot 配置日志
- 比较日志不同 log4j  log4j2 logback
  - 介绍配置日志需要考虑的事项
    - 日志路径，日志级别level (debug、info、error)
    - 日志配置
    - 日志大小、日志保存天数
    - 定义日志输出信息
    - 根据不同环境设置不同日志级别(dev、test、uat、product)
    - spring-logback.xml
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
      <configuration>
    
          <!-- 日志根目录-->
          <springProperty scope="context" name="LOG_HOME" source="logging.path" defaultValue="/data/logs/springboot-logback-demo"/>
    
          <!-- 日志级别 -->
          <springProperty scope="context" name="LOG_ROOT_LEVEL" source="logging.level.root" defaultValue="DEBUG"/>
    
          <!--  标识这个"STDOUT" 将会添加到这个logger -->
          <springProperty scope="context" name="STDOUT" source="log.stdout" defaultValue="STDOUT"/>
    
          <!-- 日志文件名称-->
          <property name="LOG_PREFIX" value="spring-boot-logback" />
    
          <!-- 日志文件编码-->
          <property name="LOG_CHARSET" value="UTF-8" />
    
          <!-- 日志文件路径+日期-->
          <property name="LOG_DIR" value="${LOG_HOME}/%d{yyyyMMdd}" />
    
          <!--对日志进行格式化-->
          <property name="LOG_MSG" value="- | [%X{requestUUID}] | [%d{yyyyMMdd HH:mm:ss.SSS}] | [%level] | [${HOSTNAME}] | [%thread] | [%logger{36}] | --> %msg|%n "/>
    
          <!--文件大小，默认10MB-->
          <property name="MAX_FILE_SIZE" value="50MB" />
    
          <!-- 配置日志的滚动时间 ，表示只保留最近 10 天的日志-->
          <property name="MAX_HISTORY" value="10"/>
    
          <!--输出到控制台-->
          <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
              <!-- 输出的日志内容格式化-->
              <layout class="ch.qos.logback.classic.PatternLayout">
                  <pattern>${LOG_MSG}</pattern>
              </layout>
          </appender>
    
          <!--输出到文件-->
          <appender name="0" class="ch.qos.logback.core.rolling.RollingFileAppender">
          </appender>
    
          <!-- 定义 ALL 日志的输出方式:-->
          <appender name="FILE_ALL" class="ch.qos.logback.core.rolling.RollingFileAppender">
              <!--日志文件路径，日志文件名称-->
              <File>${LOG_HOME}/all_${LOG_PREFIX}.log</File>
    
              <!-- 设置滚动策略，当天的日志大小超过 ${MAX_FILE_SIZE} 文件大小时候，新的内容写入新的文件， 默认10MB -->
              <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
    
                  <!--日志文件路径，新的 ALL 日志文件名称，“ i ” 是个变量 -->
                  <FileNamePattern>${LOG_DIR}/all_${LOG_PREFIX}%i.log</FileNamePattern>
    
                  <!-- 配置日志的滚动时间 ，表示只保留最近 10 天的日志-->
                  <MaxHistory>${MAX_HISTORY}</MaxHistory>
    
                  <!--当天的日志大小超过 ${MAX_FILE_SIZE} 文件大小时候，新的内容写入新的文件， 默认10MB-->
                  <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                      <maxFileSize>${MAX_FILE_SIZE}</maxFileSize>
                  </timeBasedFileNamingAndTriggeringPolicy>
    
              </rollingPolicy>
    
              <!-- 输出的日志内容格式化-->
              <layout class="ch.qos.logback.classic.PatternLayout">
                  <pattern>${LOG_MSG}</pattern>
              </layout>
          </appender>
    
          <!-- 定义 ERROR 日志的输出方式:-->
          <appender name="FILE_ERROR" class="ch.qos.logback.core.rolling.RollingFileAppender">
              <!-- 下面为配置只输出error级别的日志 -->
              <filter class="ch.qos.logback.classic.filter.LevelFilter">
                  <level>ERROR</level>
                  <OnMismatch>DENY</OnMismatch>
                  <OnMatch>ACCEPT</OnMatch>
              </filter>
              <!--日志文件路径，日志文件名称-->
              <File>${LOG_HOME}/err_${LOG_PREFIX}.log</File>
    
              <!-- 设置滚动策略，当天的日志大小超过 ${MAX_FILE_SIZE} 文件大小时候，新的内容写入新的文件， 默认10MB -->
              <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
    
                  <!--日志文件路径，新的 ERR 日志文件名称，“ i ” 是个变量 -->
                  <FileNamePattern>${LOG_DIR}/err_${LOG_PREFIX}%i.log</FileNamePattern>
    
                  <!-- 配置日志的滚动时间 ，表示只保留最近 10 天的日志-->
                  <MaxHistory>${MAX_HISTORY}</MaxHistory>
    
                  <!--当天的日志大小超过 ${MAX_FILE_SIZE} 文件大小时候，新的内容写入新的文件， 默认10MB-->
                  <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                      <maxFileSize>${MAX_FILE_SIZE}</maxFileSize>
                  </timeBasedFileNamingAndTriggeringPolicy>
              </rollingPolicy>
    
              <!-- 输出的日志内容格式化-->
              <layout class="ch.qos.logback.classic.PatternLayout">
                  <Pattern>${LOG_MSG}</Pattern>
              </layout>
          </appender>
    
          <!-- additivity 设为false,则logger内容不附加至root ，配置以配置包下的所有类的日志的打印，级别是 ERROR-->
          <logger name="org.springframework"     level="ERROR" />
          <logger name="org.apache.commons"      level="ERROR" />
          <logger name="org.apache.zookeeper"    level="ERROR"  />
          <logger name="com.alibaba.dubbo.monitor" level="ERROR"/>
          <logger name="com.alibaba.dubbo.remoting" level="ERROR" />
    
          <!-- ${LOG_ROOT_LEVEL} 日志级别 -->
          <root level="${LOG_ROOT_LEVEL}">
    
              <!-- 标识这个"${STDOUT}"将会添加到这个logger -->
              <appender-ref ref="${STDOUT}"/>
    
              <!-- FILE_ALL 日志输出添加到 logger -->
              <appender-ref ref="FILE_ALL"/>
    
              <!-- FILE_ERROR 日志输出添加到 logger -->
              <appender-ref ref="FILE_ERROR"/>
          </root>
      </configuration>

    ```
    - Profile 相关的配置可以参考:
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
        <configuration>
            <include resource="org/springframework/boot/logging/logback/base.xml" />
        
             <!-- roll by day -->
             <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">   
                <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">   
                    <fileNamePattern>logs/springboot-logback-demo.%d{yyyy-MM-dd}.log</fileNamePattern>   
                    <maxHistory>30</maxHistory>  
                </rollingPolicy>   
                <encoder>   
                    <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{35} - %msg%n</pattern>   
                </encoder>  
            </appender> 
           
            <!-- dev -->
            <logger name="org.springframework.web" level="INFO"/>
                <root level="INFO">
                <appender-ref ref="FILE" />
            </root>
        
            <!-- test or production -->
            <springProfile name="test,prod">
                <logger name="org.springframework.web" level="INFO"/>
                <logger name="com.pdai.springboot" level="INFO"/>
                <root level="INFO">
                    <appender-ref ref="FILE" />
                </root>
            </springProfile>
        
        </configuration>
    ```