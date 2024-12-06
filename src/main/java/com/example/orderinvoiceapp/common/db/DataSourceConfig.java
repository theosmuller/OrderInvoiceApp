package com.example.orderinvoiceapp.common.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import javax.sql.DataSource;
import java.sql.SQLException;

//@Configuration
//@EnableJdbcRepositories(basePackages = "com.example.orderinvoiceapp.repository.blocking")
//@PropertySource("src/main/resources/application.properties")
//public class DataSourceConfig extends HikariConfig {
//
//    @Bean
//    public DataSource dataSource() throws SQLException {
//        OracleDataSource dataSource = new OracleDataSource();
////        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
//        dataSource.setURL("jdbc:oracle:thin:@192.168.1.211:1521:XE");
//        dataSource.setUser("theosmuller");
//        dataSource.setPassword("hoivgd80");
//
//        HikariDataSource hikariDataSource = new HikariDataSource();
//        hikariDataSource.setDataSource(dataSource);
//        return hikariDataSource;
//    }
//}
