package com.quiz.controllers

import com.deepanshu.entity.Questionnaire
import com.deepanshu.entity.User
import com.fasterxml.jackson.databind.JsonMappingException
import com.quiz.daos.BasicCRUDDao
import com.quiz.daos.QuestionnaireDao
import com.quiz.daos.UserDao
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(value = "/questionnaire")
class QuestionnaireController extends JSONController{

    QuestionnaireController(){
        super(QuestionnaireController.class);
    }

    @Autowired
    private BeanFactory beanFactory;

//    BasicCRUDDao<Questionnaire> basicCRUDDao;
    // create user must go though signUp flow
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public def questionnaireCreate(HttpServletRequest request){

        def JSON = getJSON(request);

        BasicCRUDDao<User> userDao = beanFactory.getBean(BasicCRUDDao.class, User.class);;
        BasicCRUDDao<User> questionnaireDao = beanFactory.getBean(BasicCRUDDao.class, Questionnaire.class);;
        User author = userDao.get(JSON?.authorDetails);
        Questionnaire questionnaire = objectMapper.readValue(JSON?.questionnaireDetails?.toString(), Questionnaire.class);

        questionnaire.setAuthor(author);
        try{
            questionnaireDao.save(questionnaire);
        } catch(Exception e){
            println "hibernateWithSpring : QuestionnaireController : exception occured : ${e.getMessage()}"
        }
    }


    @RequestMapping(value = "/testing", method = RequestMethod.POST)
    public def testing(HttpServletRequest request){

        def JSON = getJSON(request);

        try{
           Questionnaire questionnaire = questionnaireDao.getQuestionnaire(JSON.questionnaireId);

            println(questionnaire.author);
            println(questionnaire.getAuthor());
            println("deepanshu env prop" + env.getProperty("log4j.appender.file.File"));
        } catch(Exception e){
            println "hibernateWithSpring : QuestionnaireController : exception occured : ${e.getMessage()}"
        }
    }

}
