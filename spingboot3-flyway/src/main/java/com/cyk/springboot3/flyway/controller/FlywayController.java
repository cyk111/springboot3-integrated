package com.cyk.springboot3.flyway.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyk
 * @date 2023/10/29 10:03
 */
@RestController
public class FlywayController {

    /**
     * Flyway 就是一款数据库界的版本控制工具，它可以记录数据库的变化记录
     *
     * 命令:
     * Migrate（迁移）
     * Clean（清理所有配置的对象）
     * Info（显示迁移状态和细节）
     * Validate（验证迁移规则）
     * Undo（撤消最近的迁移）
     * Baseline（建立基线）
     * Repair（修复迁移历史表）
     */

    /**
     * 安装插件 需要配置数据库 后可以执行mvn 命令
     *  mvn flyway:baseline 执行后 生成 fly_schema_history 数据库表
     *  mvn flyway:clean  删除表
     *  mvn flyway:migrate 执行命令
     *
     *
     *
     */
}
