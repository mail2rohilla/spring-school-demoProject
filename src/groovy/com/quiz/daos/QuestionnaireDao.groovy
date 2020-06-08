package com.quiz.daos

import com.deepanshu.entity.Questionnaire
import org.hibernate.Criteria

interface QuestionnaireDao {

    public List<Questionnaire> getQuestionnaires(String query);

    public List<Questionnaire> getQuestionnaires(Criteria query);

    public void saveQuestionnaire(Questionnaire theCustomer);

    public Questionnaire getQuestionnaire(String theId);

    public void deleteQuestionnaire(String theId);
}