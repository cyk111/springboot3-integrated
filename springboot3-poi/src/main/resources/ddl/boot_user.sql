create table boot_user(
    user_id       int auto_increment comment '用户ID' primary key,
    user_name     varchar(32)   null comment '用户名',
    user_sex      int default 0 null comment '性别(0:男,1:女)',
    user_age      int           null comment '年龄',
    user_password varchar(32)   null comment '密码',
    user_status   int default 0 null comment '状态(0:启用,1禁用)'
)comment '用户表';
