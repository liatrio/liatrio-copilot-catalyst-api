package com.liatrio.dojo.devopsknowledgeshareapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
	"com.liatrio.dojo.devopsknowledgeshareapi"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
