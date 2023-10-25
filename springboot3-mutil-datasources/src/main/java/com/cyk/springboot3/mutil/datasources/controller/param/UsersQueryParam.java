package com.cyk.springboot3.mutil.datasources.controller.param;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author cyk
 * @date 2023/10/25 15:55
 */
@Data
public class UsersQueryParam {

    @NotNull
    @Min(1)
    private Integer pageNo;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer pageSize;

    private String mobile;

    private String email;
}
