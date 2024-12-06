package com.example.orderinvoiceapp.common.db;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.time.Duration;

@Configuration
@EnableTransactionManagement
@EnableR2dbcRepositories(basePackages = "com.example.orderinvoiceapp.repository.reactive")
public class R2dbcConfig {

    @Bean
    public ConnectionPool connectionPool() {
        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, "oracle")
                .option(ConnectionFactoryOptions.HOST, "192.168.1.212")
                .option(ConnectionFactoryOptions.PORT, 1521)
                .option(ConnectionFactoryOptions.DATABASE, "XE")
                .option(ConnectionFactoryOptions.USER, "theosmuller")
                .option(ConnectionFactoryOptions.PASSWORD, "hoivgd80")
                .build();

        ConnectionPoolConfiguration configuration = ConnectionPoolConfiguration.builder()
                .maxSize(20) // Maximum connections in the pool
                .maxIdleTime(Duration.ofMinutes(30)) // Max idle time
                .connectionFactory(io.r2dbc.spi.ConnectionFactories.get(options))
                .build();

        return new ConnectionPool(configuration);
    }
}
