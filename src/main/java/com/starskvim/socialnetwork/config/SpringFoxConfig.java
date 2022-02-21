package com.starskvim.socialnetwork.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;


@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket actuatorApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Social Network")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.starskvim.socialnetwork"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Arrays.asList(basicAuthScheme()));
    }

    private SecurityScheme basicAuthScheme() {
        return new BasicAuth("basicAuth");
    }




}
