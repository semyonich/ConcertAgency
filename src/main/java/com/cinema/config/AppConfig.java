package com.cinema.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@PropertySource(value = "classpath:db.properties")
@ComponentScan(basePackages = {
        "com.cinema.dao",
        "com.cinema.service",
        "com.cinema.util",
        "com.cinema.security"})
public class AppConfig {
    private final Environment environment;

    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.user"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        Properties props = new Properties();
        props.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        props.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        factoryBean.setHibernateProperties(props);
        factoryBean.setPackagesToScan("com.cinema.model");
        return factoryBean;
    }
}
