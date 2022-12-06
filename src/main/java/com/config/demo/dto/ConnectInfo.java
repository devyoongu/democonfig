package com.config.demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class ConnectInfo {
    private Integer port;
    private String url;
    private String key;
}
