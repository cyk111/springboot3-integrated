CREATE TABLE `t_user` (
`user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
`nick_name` varchar(32) DEFAULT NULL COMMENT '昵称',
`mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
`password` varchar(256) DEFAULT NULL COMMENT '密码',
`secure_key` char(64) DEFAULT NULL COMMENT '密钥',
`user_head_path` varchar(64) DEFAULT NULL COMMENT '用户头像地址',
`add_time` bigint DEFAULT NULL COMMENT '创建时间',
`last_login_time` bigint DEFAULT NULL COMMENT '最后登录时间',
`login_time` int DEFAULT NULL COMMENT '登录次数',
`ip_address` varchar(16) DEFAULT NULL COMMENT 'ip地址',
`authentication` varchar(36) DEFAULT NULL COMMENT '身份码，最长期限30天',
`login_source` int DEFAULT NULL COMMENT '登录来源 1为qq,2为微信',
`real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
`sex` int DEFAULT NULL COMMENT '性别',
`birthday` bigint DEFAULT NULL COMMENT '生日',
`is_del` int DEFAULT '0' COMMENT '删除标志（0未删除，1已删除）',
PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100004 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';


INSERT INTO t_user (nick_name,mobile,password,secure_key,user_head_path,add_time,
 last_login_time,login_time,ip_address,authentication,login_source,real_name,sex,birthday,is_del)
VALUES
('bbbnnn','dfasfas','dfasfas','dfa','stdfasfring',0,0,0,'string','string',0,'dsfas',1,0,0),
('bbbnnn','dfasfas','dfasfas','dfa','stdfasfring',0,0,0,'string','string',0,'dsfas',1,0,0),
('bbbnnn','dfasfas','dfasfas','dfa','stdfasfring',0,0,0,'string','string',0,'dsfas',1,0,0);

