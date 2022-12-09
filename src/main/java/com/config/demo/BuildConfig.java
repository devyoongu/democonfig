package com.config.demo;

import org.springframework.beans.factory.annotation.Value;

public class BuildConfig {

    @Value("${logging.level.com.posicube.robi:default}")
    private String robiLogLevel = "${robiLogLevel}";

    @Value("${appName:default}")
    private String appName= "${appName}";

    @Value("${chubb.url:default}")
    private String chubbUrl= "${chubbUrl}";

    @Value("${chubb.port:2020}")
    private String chubbPort= "${chubbPort}";
}
