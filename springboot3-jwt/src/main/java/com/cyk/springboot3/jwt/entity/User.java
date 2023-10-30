package com.cyk.springboot3.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cyk
 * @date 2023/10/30 22:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {


    private Integer id;

    private String userName;

    private String password;
}
