package com.cyk.springboot3.multi.version.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cyk
 * @date 2023/11/6 15:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;
    private int age;
}
