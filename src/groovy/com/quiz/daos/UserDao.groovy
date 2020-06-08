package com.quiz.daos

import com.deepanshu.entity.User
import org.hibernate.Criteria

interface UserDao {

    public List<User> getUsers(String query);

    public List<User> getUsers(Criteria query);

    public void saveUser(User theCustomer);

    public User getUser(String theId);

    public void deleteUser(String theId);

}