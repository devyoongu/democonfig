package com.config.demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
//@Component
public class HanaDto extends CommonDto {
    @Value("${hana.port:2020}")
    private Integer port;

    @Value("${hana.url}")
    private String url;

    @Value("${hana.key}")
    private String key;

    public HanaDto() {
        System.out.println("port = " + port);
    }
}
