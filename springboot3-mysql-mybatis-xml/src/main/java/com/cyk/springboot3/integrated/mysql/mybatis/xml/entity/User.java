package com.cyk.springboot3.integrated.mysql.mybatis.xml.entity;

import lombok.Data;

/**
 * @author cyk
 * @date 2023/10/24 07:50
 */
@Data
public class User {

    private Long userId;

    private String nickName;

    private String mobile;

    private String password;

    private String secureKey;

    private String userHeadPath;

    private Long addTime;

    private String ipAddress;

    private String authentication;

    private Integer loginSource;

    private String realName;

    private Integer sex;

    private Long birthday;

    private Integer isDel;

}
