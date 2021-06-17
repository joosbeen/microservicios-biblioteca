package com.servicio.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"brave"})
public class ServicioOauth2Application {

	public static void main(String[] args) {
		SpringApplication.run(ServicioOauth2Application.class, args);
	}

}
