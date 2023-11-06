package com.cyk.springboot3.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cyk
 * @date 2023/11/6 15:59
 */
@SpringBootApplication
public class QuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class,args);
    }

    /***
     *  定时任务: 定时器操作底层多线程  任务的启动、暂停、删除 实质上就是对线程的启动、暂定、中断、唤醒
     *
     * Scheduler 调度器
     *    用来对Trigger 和 Job 进行管理,Trigger 和 JobDetail 可以注册到Scheduler中，两者在Scheduler中都拥有自己唯一的组 group 和 name
     *    scheduler 可以通过任务组和名称来对  Trigger 和 JobDetail进行管理
     *
     *    每个scheduler都有上下文，用来保存数据
     *
     *    scheduler 是由 SchedulerFactory 创建,它有两个实现：DirectSchedulerFactory 、StdSchdulerFactory ，
     *    前者可以用来在代码里定制 Schduler 参数，后者直接读取 classpath 下的 quartz.properties（不存在就都使用默认值）配置来实例化 Scheduler
     *
     *
     *  Job 任务
     *
     *  JobDetail 任务详情
     *
     *  Trigger 触发器
     *
     *  JobDataMap
     *
     *
     *  目前数据是保存在内存中，如果想保存在mysql数据库,添加 mybatis 或jpa 在service层修改，然后扫描mapper  entity 即可
     *
     *
     */
}
