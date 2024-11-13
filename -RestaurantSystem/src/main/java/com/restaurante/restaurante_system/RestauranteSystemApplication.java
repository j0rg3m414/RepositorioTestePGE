package com.restaurante.restaurante_system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RestauranteSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranteSystemApplication.class, args);
	}

}
