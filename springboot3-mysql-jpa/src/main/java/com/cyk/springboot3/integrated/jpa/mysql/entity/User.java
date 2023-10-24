package com.cyk.springboot3.integrated.jpa.mysql.entity;

import lombok.Data;

/**
 * @author cyk
 * @date 2023/10/24 07:50
 */
@Data
public class User {

    private Long id;
    private String name;
    private String address;
    private Integer age;
    private String gender;
    private String email;
    private String phone;
    private String password;

}
