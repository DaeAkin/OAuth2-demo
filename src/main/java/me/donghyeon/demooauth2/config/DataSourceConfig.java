package me.donghyeon.demooauth2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class DataSourceConfig {


    private String datasourceUrl =
            "jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf8";


    private String driverClassName = "com.mysql.jdbc.Driver";


    private String username = "root";


    private String password = "!djaak4124";

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(datasourceUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
