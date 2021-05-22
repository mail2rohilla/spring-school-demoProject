package com.quiz.controllers

import com.deepanshu.entity.Option
import com.deepanshu.entity.Questionnaire
import com.deepanshu.entity.User
import com.quiz.daos.BasicCRUDDao
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(value = "/question")
class QuestionController extends JSONController{

    QuestionController() {
        super(QuestionController.class)
    }

    @Autowired
    private BeanFactory beanFactory;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public def hibernateWithSpring(HttpServletRequest request){

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

    @RequestMapping(value = "/createOption", method = RequestMethod.POST)
    public def createOption(HttpServletRequest request){

        def JSON = getJSON(request);

        BasicCRUDDao<Option> optionDao = beanFactory.getBean(BasicCRUDDao.class, Option.class);;

        Option option = objectMapper.readValue(JSON.optionDetails.toString(), Option.class);
        logger.info("adding option with text : ${option.text}")

//        try{
            optionDao.save(option);
//        } catch(Exception e){
//            println "hibernateWithSpring : QuestionnaireController : exception occured : ${e.getMessage()}"
//        }
    }

    @RequestMapping(value = "/createAnswer", method = RequestMethod.POST)
    public def createAnswer(HttpServletRequest request){

        def JSON = getJSON(request);

        BasicCRUDDao<Option> optionDao = beanFactory.getBean(BasicCRUDDao.class, Option.class);

        Option option = objectMapper.readValue(JSON.optionDetails.toString(), Option.class);
        logger.info("adding option with text : ${option.text}")

//        try{
        optionDao.save(option);
//        } catch(Exception e){
//            println "hibernateWithSpring : QuestionnaireController : exception occured : ${e.getMessage()}"
//        }
    }

}
