package com.servicio.oauth2.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"brave", "com.servicio.oauth2.server"})
public class ServicioOauth2Application implements CommandLineRunner {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ServicioOauth2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String pass = "";
		
		for (int i = 0; i < 5; i++) {
			pass = bCryptPasswordEncoder.encode("12345");
			System.out.println(pass);
		}
		
	}

}
