package com.quiz.controllers

import com.deepanshu.entity.User
import com.quiz.daos.BasicCRUDDao
import com.quiz.daos.BasicCRUDDaoImpl
import com.quiz.daos.BasicDaosFactory
import com.quiz.daos.UserDao
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowire
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(value = "/user")
class UserController extends JSONController{


    UserController(){
        println("depnashu printing userConsttructor");
    }

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private UserDao userDao;

    // create user must go though signUp flow
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public def hibernateWithSpring(HttpServletRequest request){

        def JSON = getJSON(request);
        BasicCRUDDao<User> basicCRUDDaoUser= beanFactory.getBean(BasicCRUDDao.class, User.class);

        User user = objectMapper.readValue(JSON?.userDetails?.toString(), User.class);
//        try{
            basicCRUDDaoUser.save(user);
//        } catch(Exception e){
//            println "hibernateWithSpring : UserController -exception occured : ${e.getMessage()}"
//        }
    }

    @RequestMapping(value = "/testing", method = RequestMethod.POST)
    public def testing(HttpServletRequest request){

        def JSON = getJSON(request);

        BasicCRUDDao<User> basicCRUDDaoUser= beanFactory.getBean(BasicCRUDDao.class, User.class);
//        User user = objectMapper.readValue(JSON?.userDetails?.toString(), User.class);
        try{
            User user = basicCRUDDaoUser.get(JSON.userId);
            print("depanshu found user ${user}")
        } catch(Exception e){
            println "hibernateWithSpring : UserController -exception occured : ${e.getMessage()}"
        }
    }




    /////////////////////////////////////getters and setters////////////////////////////////////////////////////

  /*

    OBSOLETE CODE

  BasicCRUDDao<User> getUserBasicCRUDDao() {

        println("depnashu printing getting basicCrud for user");

        if(this.userBasicCRUDDao)
            return userBasicCRUDDao
        else
            return BasicDaosFactory.getBasicCRUDDaoForEntity(User.class);
    }

   */
}
