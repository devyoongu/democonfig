package com.config.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Getter
@Setter
@ToString
@Component
public class CommonDto {
    public String appName;
    public ConnectInfo hana;
    public ConnectInfo guro;
    //    public LoggingDto logging;
    public LinkedHashMap logging;
    //todo :  암/복호화, 그밖에 yml 조합룰 추가
}