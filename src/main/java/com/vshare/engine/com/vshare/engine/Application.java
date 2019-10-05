package com.vshare.engine.com.vshare.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.vshare.engine.controllers.VshareController;

@SpringBootApplication
@ComponentScan(basePackageClasses=VshareController.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
