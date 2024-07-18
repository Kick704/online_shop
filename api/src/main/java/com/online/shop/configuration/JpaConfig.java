package com.online.shop.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Необходимая конфигурация для автоматического заполнения полей {@code created} и {@code modified} у сущностей
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
