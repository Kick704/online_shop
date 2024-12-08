package com.online.shop.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * Конфигурация сваггера для отображения информации о приложении в UI документации
 */
@Configuration
public class OpenApiConfig {

    @Value("${api-title}")
    private String apiTitle;

    @Value("${api-version}")
    private String apiVersion;

    @Value("${api-description}")
    private String apiDescription;

    private String decodeToUTF8(String value) {
        return new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(decodeToUTF8(apiTitle))
                        .version(apiVersion)
                        .description(decodeToUTF8(apiDescription)));
    }

}