package com.cyk.springboot3.mutil.datasources.entity;

import lombok.Data;

/**
 * @author cyk
 * @date 2023/10/24 22:09
 */
@Data
public class Role {

    private Long roleId;
    private String roleCode;
    private String roleName;
    private Integer state;
    private Long creatorId;
    private Long modifyId;
    private Long createTime;
    private Long updateTime;

}
