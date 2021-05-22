package com.quiz.daos

import com.deepanshu.entity.User
import org.hibernate.Criteria
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    List<User> getUsers(String query) {
        return null
    }

    @Override
    List<User> getUsers(Criteria query) {
        return null
    }

    @Override
    void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession().createCriteria().uniqueResult();
        session.saveOrUpdate(user);
    }

    @Override
    User getUser(String theId) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, theId)
        return user;
    }

    @Override
    void deleteUser(String theId) {

    }


}
