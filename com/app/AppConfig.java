package com.app;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;



@Configuration
public class AppConfig {

	@Bean
	public DataSource dsobj() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/test");
		ds.setUsername("root");     ds.setPassword("root");
		return ds;
	}
	@Bean 
	public FactoryBean<SessionFactory> sfObj() {
		AnnotationSessionFactoryBean sf=new AnnotationSessionFactoryBean();
		sf.setDataSource(dsobj());
		Properties p=new Properties();
		
		p.put("hibernate dialect","org.hibernate.dialect.MySQLDialect");
		p.put("hibernate.show_sql","true");
		p.put("hibernate.hbm2ddl.auto","create");
		sf.setHibernateProperties(p);
		sf.setAnnotatedClasses(new Class[] {Employee.class});
		return sf;
	}
	@Bean
	
	public HibernateTemplate ht() {
		HibernateTemplate ht=new HibernateTemplate();
		try {
			ht.setSessionFactory(sfObj().getObject());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ht;
		}
			
		
	}
