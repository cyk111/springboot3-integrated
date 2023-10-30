package com.cyk.springboot3.redis.lettuce.entity;

import lombok.Data;

/**
 * @author cyk
 * @date 2023/10/30 20:52
 */
@Data
public class Shop {

    private Long id;
    private String shopName;
    private String address;
}
