package com.cyk.springboot3.multi.version.config;

import com.cyk.springboot3.multi.version.annotation.ApiVersionRequestMappingHandlerMapping;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author cyk
 * @date 2023/11/6 15:43
 */
@Configuration
public class CustomWebMvcConfiguration  extends WebMvcConfigurationSupport {

    @Override
    public RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new ApiVersionRequestMappingHandlerMapping();
    }
}
