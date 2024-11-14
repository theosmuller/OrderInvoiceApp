package com.example.orderinvoiceapp.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
//@PropertySource("src/main/resources/application.properties")
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@192.168.1.211:1521:XE");
        dataSource.setUsername("theosmuller");
        dataSource.setPassword("hoivgd80");
        return dataSource;
    }
}
