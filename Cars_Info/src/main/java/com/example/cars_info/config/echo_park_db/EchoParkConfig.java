package com.example.cars_info.config.echo_park_db;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories (
        basePackages = "com.example.cars_info.repository.echo_park_db",
        entityManagerFactoryRef = "echoParkDBEntityManager",
        transactionManagerRef = "echoParkDBTransactionManager"
)
public class EchoParkConfig {

    @Bean(name = "echoParkDBProperties")
    @ConfigurationProperties(prefix = "spring.datasource.echopark")
    public DataSourceProperties getDataSourceProperties () {
        return new DataSourceProperties();
    }

    @Bean (name = "echoParkDBBean")
    public DataSource getDataSource () {
        DataSourceProperties properties = getDataSourceProperties();
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean (name = "echoParkDBEntityManager")
    public LocalContainerEntityManagerFactoryBean getEntityManager (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(getDataSource())
                .packages("com.example.cars_info.model.echo_park_db")
                .build();
    }

    @Bean (name = "echoParkDBTransactionManager")
    public TransactionManager getTransactionManager (@Qualifier("echoParkDBEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }

}
