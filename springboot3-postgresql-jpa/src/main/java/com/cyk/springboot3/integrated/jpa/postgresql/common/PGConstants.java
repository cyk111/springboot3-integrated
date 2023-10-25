package com.cyk.springboot3.integrated.jpa.postgresql.common;

/**
 * @author cyk
 * @date 2023/10/25 13:43
 */
public final class PGConstants {
    /**
     * unique id generator class.
     */
    public static final String ID_GENERATOR_CONFIG = "org.hibernate.id.IncrementGenerator";

    /**
     * id generator.
     */
    public static final String ID_GENERATOR = "idGenerator";

    /**
     * no instance.
     */
    private PGConstants() {
    }
}
