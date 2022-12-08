package com.config.demo.log;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Configuration
public class CommonDynamicOptionsConfig {

    public static final CommonDynamicOptions commonDynamicOptions = CommonDynamicOptions.getInstance();

    private final LogProperties logProperties;

    @PostConstruct
    private void init() {
        System.out.println("commonDynamicOptions = " + logProperties.getRobiLogLevel());
        System.out.println("logProperties.getAppName() = " + logProperties.getAppName());
        commonDynamicOptions.setLogLevel(logProperties.getRobiLogLevel());
        commonDynamicOptions.setAppName(logProperties.getAppName());
    }
}
