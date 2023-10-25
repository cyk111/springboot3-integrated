package com.cyk.springboot3.integrated.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author cyk
 * @date 2023/10/24 07:50
 */
@Data
@Entity(name = "t_user")
public class User implements BaseEntity{

    /**
     * user id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "moblie")
    private String moblie;

    @Column(name = "password")
    private String password;

    @Column(name = "secure_key")
    private String secureKey;

    @Column(name = "user_head_path")
    private String userHeadPath;

    private Long addTimes;

    private String ipAddress;

    private String authentication;

    private Integer loginSource;

    private String realName;

    private Integer sex;

    private Long birthday;

    private Integer isDel;


}
