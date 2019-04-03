package com.inc.jmosprograms.multidimension.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
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

/**
 * @author Juan Miguel Olguin Salguero
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "persistenceEntityManagerFactory", basePackages = {
		"com.inc.jmosprograms.multidimension.repository" })

public class BackendDbConfig {

	@Primary
	@Bean(name = "persistenceDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "persistenceEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("persistenceDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.inc.jmosprograms.multidimension.entity")
				.persistenceUnit("persistence").build();
	}

	@Primary
	// @Bean(name = "persistenceTransactionManager")
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("persistenceEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
