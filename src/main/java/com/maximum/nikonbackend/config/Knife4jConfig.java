package com.maximum.nikonbackend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Knife4jConfig {
    @Bean
    public OpenAPI springShopOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("nikon-backend")
                        .description("nikon-backend interface document")
                        .version("1.0")
                        .contact(new Contact().name("maximum")
                                .email("SWE2209532@xmu.edu.my")))
                .externalDocs(new ExternalDocumentation()
                        .description("SprintBoot3 knife4j")
                        .url("http://127.0.0.1:8888"));
    }
}
