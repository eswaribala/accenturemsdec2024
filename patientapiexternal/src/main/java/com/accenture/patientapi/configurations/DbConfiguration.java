package com.accenture.patientapi.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(VaultConfiguration.class)
public class DbConfiguration {

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    private DataSourceBuilder dataSourceBuilder;
    private final VaultConfiguration vaultConfiguration;


    public DbConfiguration(VaultConfiguration vaultConfig)
    {

        this.vaultConfiguration=vaultConfig;
    }


    @Bean
    public DataSource getDataSource()
    {
        System.out.println(dbUrl);
        System.out.println(driver);

        System.out.println("Entering Given Env.....");
         System.out.println("User Name..."+vaultConfiguration.getMysqlusername());
        System.out.println("Password..."+vaultConfiguration.getMysqlpassword());

        dataSourceBuilder=DataSourceBuilder.create();
        dataSourceBuilder.url(dbUrl);
        dataSourceBuilder.username(vaultConfiguration.getMysqlusername());
        dataSourceBuilder.password(vaultConfiguration.getMysqlpassword());
        dataSourceBuilder.driverClassName(driver);
        return dataSourceBuilder.build();

    }

}
