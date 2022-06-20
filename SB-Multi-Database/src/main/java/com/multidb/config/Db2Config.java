package com.multidb.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
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
		entityManagerFactoryRef = "bookEntityManagerFactory",
		transactionManagerRef = "bookTransactionManager",
		basePackages = "com.multidb.db2.repo"
		)
public class Db2Config {

	@Primary
	@Bean(name = "bookDataSource")
	@ConfigurationProperties(prefix = "spring.db2.datasource")
	public DataSource data2Source() {
		return DataSourceBuilder.create().build();
	}
	
	
	@Primary
	@Bean(name = "bookEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("bookDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		
		return builder.dataSource(data2Source())
				.properties(properties)
				.packages("com.multidb.db2.entities")
				.persistenceUnit("db2")
				.build();
	}
	
	@Primary
	@Bean(name = "bookTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("bookEntityManagerFactory") EntityManagerFactory eniEntityManagerFactory
			) {
		return new JpaTransactionManager(eniEntityManagerFactory);
	}
	
}
