package com.noq.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.noq.db.config.MysqlConfig;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class PropertyConfig {

	@Value("${datasource.connection.username}")
	private String mysqlUserName;

	@Value("${datasource.connection.password}")
	private String mysqlPassword;

	@Value("${datasource.connection.url}")
	private String mysqlUrl;


	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	MysqlConfig mysqlConfig() {
		MysqlConfig mysqlConfig = 
				new MysqlConfig(mysqlUserName, mysqlPassword, mysqlUrl);
		return mysqlConfig;
	}
}
