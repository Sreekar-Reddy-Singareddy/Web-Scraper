package com.example.cars_info.config.allcars_db;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories (
        basePackages = "com.example.cars_info.repository.allcars_db",
        entityManagerFactoryRef = "allCarsDBEntityManager",
        transactionManagerRef = "allCarsDBTransactionManager"
)
public class AllCarsConfig {

    @Primary
    @Bean (name = "allCarsDBProperties")
    @ConfigurationProperties (prefix = "spring.datasource.allcars")
    public DataSourceProperties getDataSourceProperties () {
        return new DataSourceProperties();
    }

    @Primary
    @Bean (name = "allCarsDBBean")
    public DataSource getDataSource () {
        DataSourceProperties properties = getDataSourceProperties();
        return properties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean (name = "allCarsDBEntityManager")
    public LocalContainerEntityManagerFactoryBean getEntityManager (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(getDataSource())
                .packages("com.example.cars_info.model.allcars_db")
                .build();
    }

    @Primary
    @Bean (name = "allCarsDBTransactionManager")
    public TransactionManager getTransactionManager (@Qualifier("allCarsDBEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }


}
