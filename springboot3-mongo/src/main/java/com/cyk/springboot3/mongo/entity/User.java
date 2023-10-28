package com.cyk.springboot3.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author cyk
 * @date 2023/10/28 07:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;
    private Long id;
    private String userName;
    private String passWord;
}
