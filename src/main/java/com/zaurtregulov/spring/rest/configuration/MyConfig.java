package com.zaurtregulov.spring.rest.configuration;

/*
Данный класс ответственнен за конфигурации. Это замена applicationContext из проекта hibernate
*/

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.zaurtregulov.spring.rest") //указываем какой пакет сканируем
@EnableWebMvc //в applicationContext строка - annotation-driven
@EnableTransactionManagement //в applicationContext строка - annotation-driven transaction-manager
public class MyConfig {

  @Bean
  public DataSource dataSource() {
    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    try {
      dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
      dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC");
      dataSource.setUser("bestuser");
      dataSource.setPassword("bestuser");
    } catch (PropertyVetoException e) {
      throw new RuntimeException(e);
    }
    return dataSource;
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan("com.zaurtregulov.spring.rest.entity");

    //назначаем свойства
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    hibernateProperties.setProperty("hibernate.show_sql", "true");

    //назначение property объекту sessionFactory
    sessionFactory.setHibernateProperties(hibernateProperties);

    return sessionFactory;
  }

  @Bean
  public HibernateTransactionManager transactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    //чтобы из метода sessionFactory() получить объект SessionFactory
    //нужно вызвать метод getObject()
    transactionManager.setSessionFactory(sessionFactory().getObject());

    return transactionManager;
  }
}
