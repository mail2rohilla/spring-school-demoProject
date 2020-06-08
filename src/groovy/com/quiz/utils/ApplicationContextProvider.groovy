package com.quiz.utils

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware{

    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext ac)
        throws BeansException {
        context = ac;
    }

    public static def getBean(def beanName){
        def bean;
        Class beanClass;
//        if(beanName instanceof String)
//            beanClass = Class.forName(beanName)
//        else if(beanName instanceof Class)
//            beanClass = beanName
//        else{
//            return null;
//        }
        try{
            bean = context.getBean(beanName);
        }catch(BeansException bex){
            println "bean fetch error occured in ApplicationContextProvider.getBean() while getting bean for class ${beanName.toString()} " +
                    ", stating ${bex.getMessage()}" ;
        }catch(Exception e){
            println "unexpected error occured in ApplicationContextProvider.getBean() while getting bean for class ${beanName.toString()}" +
                    ", statig ${e.getMessage()}" ;
        }
        if(bean)
            return bean;
        else
            return null;
    }


}