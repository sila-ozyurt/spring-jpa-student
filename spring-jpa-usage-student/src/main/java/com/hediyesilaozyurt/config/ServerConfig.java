package com.hediyesilaozyurt.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "app")
@Component
public class ServerConfig {

    private List<Server> servers;
}
