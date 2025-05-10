package com.hediyesilaozyurt.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.hediyesilaozyurt"})
@EntityScan(basePackages = {"com.hediyesilaozyurt"})
@EnableJpaRepositories(basePackages = {"com.hediyesilaozyurt"})
@SpringBootApplication
public class SpringJpaUsageEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaUsageEmployeeApplication.class, args);
	}

}
