package com.quiz.daos

import com.quiz.utils.ApplicationContextProvider
import org.hibernate.Criteria
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import java.lang.reflect.ParameterizedType

class BasicCRUDDaoImpl<T> implements BasicCRUDDao<T>{


    Class<?> memberClass;

    @Autowired
    SessionFactory sessionFactory;

    BasicCRUDDaoImpl() {

    }

    BasicCRUDDaoImpl(Class aClass){
        memberClass = aClass;
    }

    @Override
    List getItems(String query) {
        return null
    }

    @Override
    List getItems(Criteria query) {
        return null
    }

    @Override
    void save(T obj) {
        Session session = sessionFactory.openSession();
        Transaction txn  = session.beginTransaction();
        session.save(obj);
        txn.commit();
        session.flush();
        session.close();
    }

    @Override

//    REQ1
//    REQ2
    T get(String theId) {
        Session session = sessionFactory.openSession();
        Transaction txn  = session.beginTransaction();
        T data =  session.get(this.memberClass , theId);
//        txn.commit();
        session.flush();
        session.close();

        return data;
    }

    @Override
    void delete(String theId) {

    }


///////////////////////getters and setters///////////////////////


    Class<?> getMemberClass() {
    if(this.memberClass == null)
        setMemberClass((Class<?>) ((ParameterizedType) this.class.getGenericSuperclass()).getActualTypeArguments()[0]);

    return this.memberClass;
    }

    void setMemberClass(Class<?> memberClass) {
        this.memberClass = memberClass
    }
}
