package com.deba.shoppingbackend.config;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

@Configuration
@ComponentScan(basePackages={"com.deba.shoppingbackend.DTO"})
@EnableTransactionManagement
public class HibernateConfiguration {

	/*private static final String DRIVER="org.h2.Driver";
	private static final String URL = "jdbc:h2:tcp://localhost/~/onlineshopping";
	private static final String USERNAEM ="sa";
	private static final String PASSWRD ="sa";
	private static final String DIALECT ="org.hibernate.dialect.H2Dialect";*/
	
	/*----------- my sql ------------*/
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/tms";
	private static final String USERNAEM ="tms";
	private static final String PASSWRD ="tms";
	private static final String DIALECT ="org.hibernate.dialect.MySQLDialect";
	@Bean
	public DataSource getDatasource()
	{
		BasicDataSource basicData = new BasicDataSource();
		basicData.setDriverClassName(DRIVER);
		basicData.setUrl(URL);
		basicData.setUsername(USERNAEM);
		basicData.setPassword(PASSWRD);
		return basicData;
	}
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder builder=new LocalSessionFactoryBuilder(dataSource);
		builder.setProperty("hibernate.dialect", DIALECT);
		builder.setProperty("hibernate.show_sql", "true");
		builder.setProperty("hibernate.format_sql", "true");
		builder.setProperty("hibernate.hbm2ddl.auto", "update");
		builder.scanPackages("com.deba.shoppingbackend.DTO");
		SessionFactory factory=null;
		try {
			factory= builder.buildSessionFactory();
			return factory;
		} catch (Exception e) {
			System.out.println("factory exception "+e);
		}
		
		return factory;
	}

	private Properties getProperties() {
		Properties properties=new Properties();
		//properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		/*properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");*/
		System.out.println("proper ties"+properties);
		return properties;
	}
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}
