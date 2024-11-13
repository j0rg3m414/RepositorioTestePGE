package com.restaurante.MS_CLOUD_GATEWAY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * Irá se registrar no server Eureka
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class MsCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCloudGatewayApplication.class, args);
	}

	/**
	 * Fará o roteamento das rotas
	 * direto para o disvovery server
	 * Será injetado pelo Spring
	 * Configurado uma porta fixa
	 * @return
	 */
	@Bean
	public RouteLocator routers(RouteLocatorBuilder builder){
		return builder
				.routes()
				.route(r -> r.path("/menu/**").uri("lb://ms-restaurante-eureka-client"))
				.route(r -> r.path("/pedidos/**").uri("lb://ms-restaurante-eureka-client"))
				.route(r -> r.path("/cozinha/**").uri("lb://ms-cozinha"))
				.build();
	}

}
