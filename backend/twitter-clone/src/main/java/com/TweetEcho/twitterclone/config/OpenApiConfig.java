package com.TweetEcho.twitterclone.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String description,
                                 @Value("${application-version}") String version,
                                 @Value("${application-license}") String license) {
        return new OpenAPI()
                .info(new Info().title("Twitter Clone API")
                        .description(description)
                        .license(new License().name(license))
                        .version(version));
    }
}