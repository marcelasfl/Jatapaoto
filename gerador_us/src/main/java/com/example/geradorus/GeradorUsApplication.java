package com.example.geradorus;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Controller View - Dev Web",
        version = "1.0",
        description = "Visualização dos controllers existentes no sistema"))
public class GeradorUsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeradorUsApplication.class, args);
    }
}
