package com.config.demo;

import com.config.demo.config.BuildConfig;
import com.config.demo.config.BuildConfigDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		if (args.length > 0) {
			BuildConfigDto buildConfigDto = new BuildConfigDto(args[0]);
			buildConfigDto.generateConfigYml();
		}

//		BuildConfigDto BuildConfigDto = new BuildConfigDto("hana");
//		BuildConfigDto.generateConfigYml();
	}
}
