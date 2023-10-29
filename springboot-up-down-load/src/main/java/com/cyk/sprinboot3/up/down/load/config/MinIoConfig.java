package com.cyk.sprinboot3.up.down.load.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author cyk
 * @date 2023/10/29 15:37
 */
@Data
@Configuration
public class MinIoConfig {

    @Value(value = "${minIo.endpoint}")
    private String endpoint;
    @Value(value = "${minIo.accessKey}")
    private String accessKey;
    @Value(value = "${minIo.secretKey}")
    private String secretKey;
}
