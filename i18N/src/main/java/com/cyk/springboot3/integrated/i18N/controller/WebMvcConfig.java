package com.cyk.springboot3.integrated.i18N.controller;

import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @author cyk
 * @date 2023/10/24 17:43
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * message source.
     */
    private final ResourceBundleMessageSource resourceBundleMessageSource;

    public WebMvcConfig(ResourceBundleMessageSource resourceBundleMessageSource) {
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    /**
     * lang param name in header, default to 'locale'.
     */
    private static final String LANGUAGE_PARAM_NAME = LocaleChangeInterceptor.DEFAULT_PARAM_NAME;



    /**
     * default locale.
     *
     * @return locale resolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }

    /**
     * local validator factory bean.
     *
     * @return LocalValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        MessageInterpolatorFactory interpolatorFactory = new MessageInterpolatorFactory();
        factoryBean.setMessageInterpolator(interpolatorFactory.getObject());
        factoryBean.setValidationMessageSource(resourceBundleMessageSource);
        return factoryBean;
    }

    /**
     * locale change interceptor.
     *
     * @return LocaleChangeInterceptor
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new CustomLocaleChangeInterceptor();
        interceptor.setParamName(LANGUAGE_PARAM_NAME);
        return interceptor;
    }

    /**
     * register locale change interceptor.
     *
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
