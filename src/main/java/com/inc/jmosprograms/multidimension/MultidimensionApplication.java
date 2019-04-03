package com.inc.jmosprograms.multidimension;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Juan Miguel Olguin Salguero
 *
 */
@SpringBootApplication
@EnableScheduling
public class MultidimensionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultidimensionApplication.class, args);

	}

	public MultidimensionApplication() {

	}

}