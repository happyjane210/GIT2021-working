package com.git.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloControllerApplication {

	public static void main(String[] args) {
		// HelloControllerApplication 얘 자체를 넣어서 실행함
		SpringApplication.run(HelloControllerApplication.class, args);
	}

}
