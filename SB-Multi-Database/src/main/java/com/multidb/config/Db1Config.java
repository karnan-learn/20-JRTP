package com.multidb.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableJpaRepositories(
		entityManagerFactoryRef = "entityManagerFactory",
		basePackages = "com.multidb.db1.repo"
		)
public class Db1Config {

	@Primary
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.db1")
	public DataSource data1Source() {
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder builder,
			@Qualifier("datasource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
		
		return builder.dataSource(data1Source())
				.properties(properties)
				.packages("com.multidb.db1.entities")
				.persistenceUnit("db1")
				.build();
	}
	
	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory eniEntityManagerFactory
			) {
		return new JpaTransactionManager(eniEntityManagerFactory);
	}
}
