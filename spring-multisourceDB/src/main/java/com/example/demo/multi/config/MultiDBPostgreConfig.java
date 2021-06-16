package com.example.demo.multi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;




@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "postgreEntityManagerFactory",
    transactionManagerRef = "postgreTransactionManager", basePackages = {"com.example.demo.multi.postgre.repo"})
public class MultiDBPostgreConfig {

  @Autowired Environment env;
  
  @Primary
  @Bean(name = "postgreDataSource")
  @ConfigurationProperties(prefix = "postgre.datasource")
  public DataSource dataSource() {
	  	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(env.getProperty("postgre.datasource.driver-class-name"));
	    dataSource.setUrl(env.getProperty("postgre.datasource.url"));
	    dataSource.setUsername(env.getProperty("postgre.datasource.username"));
	    dataSource.setPassword(env.getProperty("postgre.datasource.password"));

	    return dataSource;
  }
  
  @Primary
  @Bean(name = "postgreEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean postgreEntityManagerFactory(
          EntityManagerFactoryBuilder builder,
          @Qualifier("postgreDataSource") DataSource dataSource
  ) {
      return builder
              .dataSource(dataSource)
              .packages("com.example.demo.multi.postgre.model")
              .persistenceUnit("postgreDb")
              .build();
  }
  
  @Bean(name = "postgreTransactionManager")
  @Primary
  public DataSourceTransactionManager postgreTransactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
      return new DataSourceTransactionManager(dataSource);
  }
}