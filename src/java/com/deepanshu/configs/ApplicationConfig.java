package com.deepanshu.configs;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.quiz.filters.RequestToJsonFilter;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.List;

@ComponentScan({"com.quiz", "com.deepanshu"})
@EnableTransactionManagement
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class ApplicationConfig  implements WebMvcConfigurer {


    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }
    public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        System.out.println("deepnashu adding converters");
        messageConverters.add(new MappingJackson2HttpMessageConverter());
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&amp");
        dataSource.setUser("hbstudent");
        dataSource.setPassword("hbstudent");

        dataSource.setInitialPoolSize(5);
        dataSource.setMinPoolSize(5);
        dataSource.setMaxPoolSize(20);
        dataSource.setMaxIdleTime(30000);

        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {

        org.springframework.orm.hibernate5.LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.scanPackages("com.deepanshu.entity");
        sessionBuilder.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        sessionBuilder.setProperty("hibernate.show_sql", "true");

        return sessionBuilder.buildSessionFactory();
    }
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

        return transactionManager;
    }

    @Bean
    public FilterRegistrationBean<RequestToJsonFilter> JsonConverterFilter() {

        FilterRegistrationBean<RequestToJsonFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RequestToJsonFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }
}
