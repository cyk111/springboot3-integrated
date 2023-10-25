package com.cyk.springboot3.integrated.jpa.postgresql.entity;

import com.cyk.springboot3.integrated.jpa.postgresql.common.PGConstants;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

/**
 * @author cyk
 * @date 2023/10/24 22:09
 */
@Data
@Entity
@Table(name = "tb_role")
public class Role implements BaseEntity{

    /**
     * role id.
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = PGConstants.ID_GENERATOR)
    @GenericGenerator(name = PGConstants.ID_GENERATOR, strategy = PGConstants.ID_GENERATOR_CONFIG)
    private Long id;

    private String name;

    private String roleKey;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
