package com.fitness.activityservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActivityserviceApplication {

	public static void main(String[] args) {
		// ✅ Load .env file BEFORE Spring Boot starts
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);

		// ✅ Now start Spring Boot
		SpringApplication.run(ActivityserviceApplication.class, args);
	}
}
