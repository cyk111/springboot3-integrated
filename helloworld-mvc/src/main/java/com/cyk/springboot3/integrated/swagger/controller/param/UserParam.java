package com.cyk.springboot3.integrated.swagger.controller.param;

/**
 * @author cyk
 * @date 2023/10/24 07:59
 */
public class UserParam {

    private String userName;
    private Integer age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
