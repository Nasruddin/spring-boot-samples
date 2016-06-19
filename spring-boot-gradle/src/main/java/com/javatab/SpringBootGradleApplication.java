package com.javatab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGradleApplication.class, args);
	}

	@RequestMapping("/")
	public static String sayHello() {
		return "You are Good";
	}
}
