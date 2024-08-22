package com.docbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DocbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocbookApplication.class, args);
	}

}
