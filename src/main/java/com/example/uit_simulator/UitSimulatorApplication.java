package com.example.uit_simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class UitSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(UitSimulatorApplication.class, args);
	}

}
