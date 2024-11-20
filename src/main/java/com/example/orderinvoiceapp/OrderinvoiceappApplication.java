package com.example.orderinvoiceapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
//import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
@ComponentScan(basePackages={"com.example.orderinvoiceapp"})
@Slf4j
//@EntityScan(basePackages={"com.example.orderinvoiceapp"})
@EnableJdbcRepositories(basePackages = "com.example.orderinvoiceapp.repository.blocking")
//@EnableR2dbcRepositories(basePackages = "com.example.orderinvoiceapp.repository.reactive")
//@EnableAutoConfiguration
public class OrderinvoiceappApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderinvoiceappApplication.class, args);
    }

}
