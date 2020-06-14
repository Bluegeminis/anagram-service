package com.bluegeminis.anagram.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bluegeminis.anagram.AnagramGenerator;
import com.bluegeminis.anagram.AnagramValidator;

@SpringBootApplication
public class AnagramApplication {

	// Define singletons helpers
	@Bean
	public AnagramValidator anagramValidator() {
		return new AnagramValidator();
	}

	@Bean
	public AnagramGenerator anagramGenerator() {
		return new AnagramGenerator();
	}

	public static void main(String args[]) {
		SpringApplication.run(AnagramApplication.class, args);
	}

}
