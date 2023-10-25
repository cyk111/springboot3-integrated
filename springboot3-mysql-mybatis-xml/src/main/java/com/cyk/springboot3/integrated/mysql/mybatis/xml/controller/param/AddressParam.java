package com.cyk.springboot3.integrated.mysql.mybatis.xml.controller.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author cyk
 * @date 2023/10/24 14:57
 */
@Data
public class AddressParam {
    @NotBlank
    private String province;
    @NotBlank
    private String city;
}
