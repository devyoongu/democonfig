package com.config.demo.controller;

import com.config.demo.service.DemoService;
import lombok.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/demo2")
    public String getLogLevel2(@RequestBody ReqDto reqDto, BindingResult bindingResult) {
//    public String getLogLevel2(@ModelAttribute ReqDto reqDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("bindingResult = " + bindingResult);
        }

        System.out.println("reqDto.getAppName() = " + reqDto.getAppName());

        return reqDto.getAppName();
    }

    @Data
    @AllArgsConstructor
    static class ReqDto {
        private String appName;
        private String loggingLevel;
    }


}
