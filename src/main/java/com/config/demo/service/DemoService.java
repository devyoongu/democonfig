package com.config.demo.service;

import com.config.demo.config.BuildConfigDto;
import com.config.demo.config.GenerateConfig;
import com.config.demo.log.CommonDynamicOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;

import static com.config.demo.log.PosiLogger.*;

@Service
@RequiredArgsConstructor
public class DemoService {
    private final GenerateConfig generateConfig;
    public static final CommonDynamicOptions commonDynamicOptions = CommonDynamicOptions.getInstance();
    public void generateConfig(String logLevel, String appName) {
        generateConfig.generateConfigYml(appName);
    }

    public String setLogLevel(String logLevel, String appName) {
        commonDynamicOptions.setLogLevel(logLevel);
        commonDynamicOptions.setAppName(appName);
        String updatedAppName = commonDynamicOptions.getAppName();
        trace("trace");
        debug("debug");
        info("info");
        warn("warn");
        error("error");

        return updatedAppName;
    }
}
