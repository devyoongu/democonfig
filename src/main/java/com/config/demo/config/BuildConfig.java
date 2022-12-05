package com.config.demo.config;

import com.config.demo.DemoApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@RequiredArgsConstructor
public class BuildConfig {
    private final String appName;

    public void generateConfig() {
        System.out.println("appName = " + appName);

        Map<String, Object> map = createMap();

        //yml 생성 옵션
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);

        Yaml yaml = new Yaml(options);

        try {
            PrintWriter writer = new PrintWriter(new File("./src/main/resources/customer.yml"));
            yaml.dump(map, writer);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Object> createMap() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource("default.yml").getFile());
        ObjectMapper om = new ObjectMapper(new YAMLFactory());

        Map<String, Object> map = new HashMap<>();


        try {
            Map<String, Object> fileMap = om.readValue(file, HashMap.class);

            List<String> appNames = Arrays.asList(String.valueOf(fileMap.get("appNames")).split(","));

            for (String key : fileMap.keySet()) {
                if (key.equals(this.appName)) { // 특정 기관정보만 get
                    map.put(key, getMapValue(fileMap, key));
                }
                if (!appNames.contains(key)) { // 기관정보 이외 공통정보 get
                    map.put(key, getMapValue(fileMap, key));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return map;
    }

    private Object getMapValue(Map<String, Object> fileMap, String key) {

        if (fileMap.get(key) instanceof LinkedHashMap) {

            Map innerMap = (Map) fileMap.get(key);
            Map<String, Object> valueMap = new HashMap<>();

            for (Object o : innerMap.keySet()) {
                valueMap.put((String) o, innerMap.get(o) instanceof String ? innerMap.get(o).toString() : innerMap.get(o));
            }

            return valueMap;
        }

        return fileMap.get(key);
    }

}