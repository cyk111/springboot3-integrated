package com.cyk.springboot3.integrated.jpa.postgresql.entity;

import com.cyk.springboot3.integrated.jpa.postgresql.common.PGConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

/**
 * @author cyk
 * @date 2023/10/24 07:50
 */
@Data
@Entity
@Table(name = "tb_user")
public class User implements BaseEntity{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = PGConstants.ID_GENERATOR)
    @GenericGenerator(name = PGConstants.ID_GENERATOR, strategy = PGConstants.ID_GENERATOR_CONFIG)
    private Long id;

    private String userName;

    @JsonIgnore
    private String password;

    private String email;

    private long phoneNumber;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
