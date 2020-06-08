package com.quiz.daos

import com.deepanshu.entity.Questionnaire
import com.deepanshu.entity.User
import org.hibernate.Criteria
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class QuestionnaireDaoImpl implements  QuestionnaireDao{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    List<Questionnaire> getQuestionnaires(String query) {
        return null
    }

    @Override
    List<Questionnaire> getQuestionnaires(Criteria query) {
        return null
    }

    @Override
    void saveQuestionnaire(Questionnaire questionnaire) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(questionnaire);

    }

    @Override
    Questionnaire getQuestionnaire(String theId) {
        Session session = sessionFactory.getCurrentSession();
        Questionnaire questionnaire = session.get(Questionnaire.class, theId)
        return questionnaire;
    }

    @Override
    void deleteQuestionnaire(String theId) {

    }
}
