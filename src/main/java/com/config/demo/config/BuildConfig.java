package com.config.demo.config;

import com.config.demo.dto.CommonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.RequiredArgsConstructor;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.*;
import java.util.*;
@RequiredArgsConstructor
public class BuildConfig {

    private final String appName;

    public void generateConfigYml() {
        /*Map<String, Object> fileMap = fileRead("customer.yml");
        if (fileMap.get("appName") != null) {
            return;
        }*/

        //1) 외부 dld yml -> Map convert
        Map<String, Object> ymlMap = fileRead("dld.yml");

        //2) yml 변환전 Map 생성
        Map<String, Object> map = createMap(ymlMap);

        //3) 기관 customer yml 생성
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

    private Map<String, Object> createMap(Map<String, Object> ymlMap) {
        Map<String, Object> map = new HashMap<>();

        List<String> appNames = Arrays.asList(String.valueOf(ymlMap.get("appNames")).split(","));

        for (String key : ymlMap.keySet()) {
            map.put(key, getMapValue(ymlMap, key));
            map.put("appName", key);
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

    private Map<String, Object> fileRead(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        Map<String, Object> fileMap = new HashMap<>();

        try {
            fileMap = om.readValue(file, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileMap;
    }

}