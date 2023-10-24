package com.cyk.springboot3.integrated.swagger.controller.param;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author cyk
 * @date 2023/10/24 07:59
 */
@Data
public class UserParam {

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotNull(message = "age 不能为空")
    private Integer age;
}
