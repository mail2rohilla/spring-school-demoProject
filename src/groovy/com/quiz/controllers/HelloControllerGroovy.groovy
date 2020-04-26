package com.quiz.controllers

import com.deepanshu.pojos.Demo1
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper
import org.hibernate.Query
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.json.JSONException;
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*

import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/controllerMapGroovy")
public class HelloControllerGroovy extends JSONController{


    @Autowired
    private SessionFactory sessionFactory;

    private class Student{
        String name;
        String classRoom;
        String date;

        Student(String name, String classRoom, String date) {
            this.name = name
            this.classRoom = classRoom
            this.date = date
        }
    }
    @RequestMapping(value = "/helloGroovy", method = RequestMethod.POST)
    public def demoFunc(HttpServletRequest request) {
        def JSON = getJSON(request);
        def dem;
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            InputStream inp = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inp));
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/
            System.out.println(e);}
        System.out.println("deepu " + jb.toString());
        try{
            JSONObject obj = new JSONObject(jb.toString());

            ObjectMapper om = new ObjectMapper();
             dem = (Demo1)om.readValue(obj.get("demoVar").toString(), Demo1.class);

        }catch(JSONException ex){
            System.out.println("exception occured while parssing");
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Student st = new Student("deepanshu", "12", "12-23-3444")
        def temp = new com.deepanshu.pojos.Demo(1,2,3,4, new Demo1(3,4,5))
        return st;
    }

    @RequestMapping(value = "/helloHibernate", method = RequestMethod.POST)
    @Transactional
    @ResponseBody
    public def hibernateWithSpring(@RequestParam Integer id, HttpServletRequest request){

        def JSON=getJSON(request);
        Session session;
        List list;
        com.deepanshu.entity.Student student;
        try {

            session = sessionFactory.getCurrentSession();
            // start a transaction
//            session.beginTransaction();
            String hql = "from Student";
            Query query = session.createQuery(hql,com.deepanshu.entity.Student.class);
            list = query.list();
            print("student fetched is  ${list}");

            // commit transaction
//            session.getTransaction().commit();

            System.out.println("Done!");
        }
        catch(Exception e){
            print("deepanshuu exception "+ e.getMessage());
        }
        finally {


        }

//        return (com.deepanshu.entity.Student)student;
        return list;
    }
}
