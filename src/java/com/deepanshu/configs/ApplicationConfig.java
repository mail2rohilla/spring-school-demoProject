package com.deepanshu.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.quiz.daos.BasicCRUDDao;
import com.quiz.daos.BasicCRUDDaoImpl;
import com.quiz.filters.RequestToJsonFilter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentScan({"com.quiz", "com.deepanshu"})
@EnableTransactionManagement
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@PropertySource("classpath:application.properties")
public class ApplicationConfig  implements WebMvcConfigurer {


    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApplicationConfig.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
        app.run(args);
    }
    public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        System.out.println("deepnashu adding converters");
        messageConverters.add(new MappingJackson2HttpMessageConverter());
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/studyApp?useSSL=false&amp");
        dataSource.setUser("student_user");
        dataSource.setPassword("student_password");

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
        sessionBuilder.setProperty("hibernate.current_session_context_class", "thread");
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

    @Bean(name = "objectMapper")
    public ObjectMapper getObjectMapper() {

        return new ObjectMapper();
    }

    private Map<Class<?> , BasicCRUDDaoImpl<?>> basicCrudMap;

    @Bean(name = "basicCRUDDao")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.DEFAULT)
    public BasicCRUDDao<?> basicCRUD(Class<?> aClass) {
        BasicCRUDDaoImpl retval = null;
        if(basicCrudMap == null)
            basicCrudMap = new HashMap<Class<?>, BasicCRUDDaoImpl<?>>();

        if(basicCrudMap.containsKey(aClass) && basicCrudMap.get(aClass) != null)
            retval = basicCrudMap.get(aClass);
        else{
            retval = new BasicCRUDDaoImpl(aClass);
            basicCrudMap.put(aClass, retval);
        }
        System.out.println("deepanshu property print" + basicCrudMap + " " + aClass);
        return retval;
    }


}


// TODO : remove all additoinal jar's and verify on tomcat server manually