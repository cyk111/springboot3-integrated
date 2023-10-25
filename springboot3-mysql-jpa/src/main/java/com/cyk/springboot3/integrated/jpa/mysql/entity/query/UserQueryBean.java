package com.cyk.springboot3.integrated.jpa.mysql.entity.query;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Min(1)
    private Integer pageNo;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer pageSize;
}
