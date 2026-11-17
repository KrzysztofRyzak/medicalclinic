package com.KrzysztofRyzak.medicalclinic.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // adnotacja mowiaca ze klasa jest beanem springowym oraz jest to konfiguracja czyli definiujemy tutaj rowniez beany

public class MyAppConfiguration {

    @Bean("x")
    String string() { return "x"; }

    @Bean("y")
    String stringY() { return "y"; }
}
