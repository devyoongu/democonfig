package com.config.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
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
    public LinkedHashMap logging;
}
