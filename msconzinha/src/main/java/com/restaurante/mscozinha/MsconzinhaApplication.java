package com.restaurante.mscozinha;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //vai ser registrar como cliente do server
@EnableRabbit
public class MsconzinhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsconzinhaApplication.class, args);
	}

}
