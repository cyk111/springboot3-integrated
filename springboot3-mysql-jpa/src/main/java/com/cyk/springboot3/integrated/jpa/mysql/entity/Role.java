package com.cyk.springboot3.integrated.jpa.mysql.entity;

import lombok.Data;

import jakarta.persistence.*;

/**
 * @author cyk
 * @date 2023/10/24 22:09
 */
@Data
@Entity(name = "t_role")
public class Role implements BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    private String roleCode;
    private String roleName;
    private Integer state;
    private Long creatorId;
    private Long modifyId;
    private Long createTime;
    private Long updateTime;
}
