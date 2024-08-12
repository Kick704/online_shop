package com.online.shop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Необходимая конфигурация для автоматического заполнения полей {@code created} и {@code modified} у сущностей
 */
@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class JpaConfig {

    /**
     * Надстройка для установки времени в определенной временной зоне независимо от местоположения сервера
     */
    @Bean
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(ZonedDateTime.now(ZoneOffset.UTC));
    }

}
