package com.quiz.daos

import com.deepanshu.entity.Questionnaire
import org.hibernate.Criteria

public interface BasicCRUDDao<T> {

    public List<T> getItems(String query);

    public List<T> getItems(Criteria query);

    public void save(T obj);

    public T get(String theId);

    public void delete(String theId);
}
