package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.env.Environment;

//@SpringBootApplication = (默认属性)@Configuration + @EnableAutoConfiguration + @ComponentScan。
@SpringBootApplication(scanBasePackages = "com.example")
@ServletComponentScan
public class Application {
	public static void main(String[] args) {
//		SpringApplication.run(HelloWorldController.class, args);
		SpringApplication app = new SpringApplication(Application.class);
        Environment environment = app.run(args).getEnvironment();
	}
}
