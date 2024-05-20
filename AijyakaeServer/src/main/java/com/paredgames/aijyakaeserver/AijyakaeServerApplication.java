package com.paredgames.aijyakaeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AijyakaeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AijyakaeServerApplication.class, args);
	}

}
