package com.kulklex;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("appConfig")
public record AppConfig(String DB_PASSWORD) {

}
