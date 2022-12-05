package com.config.demo.log;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 1. logging 관련 properties를 yaml로부터 읽어온다. 2. 추후 Trigger 기능과 같이 사용할 때, Default에 대한 기능으로 사용할 예정
 */
@ToString
@Getter
@Setter
@Component
public class LogProperties {

    @Value("${logging.level.com.posicube.robi}")
    private String robiLogLevel;
}