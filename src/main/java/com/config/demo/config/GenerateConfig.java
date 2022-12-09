package com.config.demo.config;

import com.config.demo.dto.CommonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.*;

@RequiredArgsConstructor
@Component
public class GenerateConfig {
    public static final String SRC_MAIN_RESOURCES = "./src/main/resources/";
    public static final String DLD_YML = "dld.yml";
    public static final String CUSTOMER_YML = "customer.yml";
//    private final String appName;

    public void generateConfigYml(String appName) {
        CommonDto commonDto = convertModel(DLD_YML, appName);

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);

        Yaml yaml = new Yaml(options);

        try {
            PrintWriter writer = new PrintWriter(new File(SRC_MAIN_RESOURCES+CUSTOMER_YML));
            yaml.dump(commonDto, writer);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /** yml -> DTO */
    private CommonDto convertModel(String targetYml, String appName) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(SRC_MAIN_RESOURCES+targetYml));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Representer representer = new Representer();//DTO에 정의 되어 있지 않아도 빌드 오류 방지 코드
        representer.getPropertyUtils().setSkipMissingProperties(true);

        Yaml yaml = new Yaml(new Constructor(CommonDto.class), representer);
        CommonDto commonDto = yaml.load(inputStream);
        commonDto.setAppName(appName);

        System.out.println("commonDto = " + commonDto);

        return commonDto;
    }


}