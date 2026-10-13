package com.java.example.springai.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI aiLearningOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AI Learning API")
                        .version("1.0")
                        .description("Hands-on AI learning project using Spring AI and Gemini"));
    }
}
