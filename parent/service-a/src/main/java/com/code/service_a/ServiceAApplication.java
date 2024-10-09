package com.code.service_a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableScheduling
public class ServiceAApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAApplication.class, args);
	}
	
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
