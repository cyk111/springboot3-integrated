package com.cyk.springboot3.mutil.datasources.common;

/**
 * @author cyk
 * @date 2023/10/24 08:10
 */
public enum GenderEnums {

    MAN("0","男"),
    WOMEN("1","女");

    private String value;
    private String desc;

    GenderEnums(String value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
