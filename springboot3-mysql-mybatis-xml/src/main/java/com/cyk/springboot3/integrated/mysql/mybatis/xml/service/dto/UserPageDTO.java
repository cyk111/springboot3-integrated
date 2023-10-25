package com.cyk.springboot3.integrated.mysql.mybatis.xml.service.dto;

import lombok.Data;

/**
 * @author cyk
 * @date 2023/10/25 15:48
 */
@Data
public class UserPageDTO  extends PageBase {
    private String loginName;

    private String email;
}
