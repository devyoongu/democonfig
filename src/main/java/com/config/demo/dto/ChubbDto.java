package com.config.demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class ChubbDto extends CommonDto {
    @Value("${chubb.port:2020}")
    private Integer port;

    @Value("${chubb.url}")
    private String url;

    @Value("${chubb.key}")
    private String key;
}
