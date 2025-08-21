package com.hediyesilaozyurt.starter;

import com.hediyesilaozyurt.config.ServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = {"com.hediyesilaozyurt"})
@EntityScan(basePackages = {"com.hediyesilaozyurt"})
@EnableJpaRepositories(basePackages = {"com.hediyesilaozyurt"})
//@PropertySource(value = "classpath:app.properties")
@EnableConfigurationProperties(value= ServerConfig.class)
@EnableScheduling
@SpringBootApplication
public class SpringJpaUsageEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaUsageEmployeeApplication.class, args);
	}

}
