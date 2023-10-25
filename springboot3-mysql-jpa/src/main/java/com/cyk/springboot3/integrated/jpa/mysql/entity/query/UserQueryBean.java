package com.cyk.springboot3.integrated.jpa.mysql.entity.query;

import lombok.Builder;
import lombok.Data;

/**
 * @author cyk
 * @date 2023/10/24 22:29
 */
@Data
@Builder
public class UserQueryBean {

    private String name;
    private String description;
}
