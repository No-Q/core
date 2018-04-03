package com.noq.db.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.noq.db.dao")
public class DataSourceConfig {

	private static final String MODEL_PACKAGE = "com.noq.db.model";
	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static final String HIBERNATE_HBM2DDL_AUTO = "update";
	private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQLDialect";
	private static final String HIBERNATE_SESSION_CLASS = "thread";

	@Autowired
	private MysqlConfig mysqlConfig;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.MYSQL);
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(MODEL_PACKAGE);
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER_CLASS);
		dataSource.setUrl(mysqlConfig.getMysqlUrl());
		dataSource.setUsername(mysqlConfig.getMysqlUserName());
		dataSource.setPassword(mysqlConfig.getMysqlPassword());

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto",HIBERNATE_HBM2DDL_AUTO);
		properties.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
		properties.setProperty("hibernate.current_session_context_class", HIBERNATE_SESSION_CLASS);
		return properties;
	}

}