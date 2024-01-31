package com.boot.demo.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @Configuration: mark a class as a source for beans definition
// @ComponentScan: enables component scanning and register them to the application context
// @EnableAutoConfiguration: triggered by several conditions (initialize embedded tomcat)
public class CatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }

}
