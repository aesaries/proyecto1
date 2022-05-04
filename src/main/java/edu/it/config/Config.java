package edu.it.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;

@Configuration
public class Config {
	@Bean
	public Faker crearBacker() {
		return new Faker();
	}
}
