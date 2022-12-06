package com.config.demo;

import com.config.demo.config.BuildConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		if (args.length > 0) {
			System.out.println("args = " + args[0]);
			BuildConfig buildConfig = new BuildConfig(args[0]);
			buildConfig.generateConfig();
		}
	}
}
