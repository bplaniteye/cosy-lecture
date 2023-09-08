package com.projet.library.config;

import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(false);
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("dd-yyyy-MM HH:mm"));
        registrar.registerFormatters(registry);
    }
}

