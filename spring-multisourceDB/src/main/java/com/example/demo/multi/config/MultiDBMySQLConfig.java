package com.example.demo.multi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlEntityManagerFactory",
    transactionManagerRef = "mysqlTransactionManager", basePackages = {"com.example.demo.multi.mysql.repo"})
public class MultiDBMySQLConfig {

	
  @Autowired Environment env;

  @Bean(name = "mysqlDataSource")
  @ConfigurationProperties(prefix = "mysql.datasource")
  public DataSource dataSource() {
	  	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(env.getProperty("mysql.datasource.driver-class-name"));
	    dataSource.setUrl(env.getProperty("mysql.datasource.url"));
	    dataSource.setUsername(env.getProperty("mysql.datasource.username"));
	    dataSource.setPassword(env.getProperty("mysql.datasource.password"));

	    return dataSource;
  }
  
  @Bean(name = "mysqlEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
          EntityManagerFactoryBuilder builder,
          @Qualifier("mysqlDataSource") DataSource dataSource
  ) {
      return builder
              .dataSource(dataSource)
              .packages("com.example.demo.multi.mysql.model")
              .persistenceUnit("mysqlDb")
              .build();
  }
  
  @Bean(name = "mysqlTransactionManager")
  public DataSourceTransactionManager mysqlTransactionManagerr(@Qualifier("mysqlDataSource") DataSource dataSource) {
      return new DataSourceTransactionManager(dataSource);
  }
}
