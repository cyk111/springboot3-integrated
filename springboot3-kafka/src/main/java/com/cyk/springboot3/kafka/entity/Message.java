package com.cyk.springboot3.kafka.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author cyk
 * @date 2023/10/31 19:35
 */
@Data
public class Message {

    private Long id;

    private String msg;

    private Date sendTime;
}
