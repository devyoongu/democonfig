package com.config.demo.controller;

import com.config.demo.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DemoController {

    private final DemoService demoService;

    @GetMapping("/demo")
    public String getLogLevel() {

        String logLevel = demoService.DemoLog();

        return logLevel;
    }


}
