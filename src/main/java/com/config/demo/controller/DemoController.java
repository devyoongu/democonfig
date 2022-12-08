package com.config.demo.controller;

import com.config.demo.DemoApplication;
import com.config.demo.dto.ReqDto;
import com.config.demo.log.PosiLogger;
import com.config.demo.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.config.demo.log.PosiLogger.*;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final DemoService demoService;

    @GetMapping("/demo")
    public String getLogLevel(@RequestParam("appname") String appName,@RequestParam("loglevel") String logLevel ) {

        demoService.generateConfig(logLevel, appName);
        String updatedAppName = demoService.setLogLevel(logLevel, appName);

        return updatedAppName;
    }


}
