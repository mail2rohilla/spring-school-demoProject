package com.quiz.controllers

import com.deepanshu.entity.School

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.hibernate.Query
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.json.JSONException;
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.json.JsonParseException
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

//        String getName() {
//            return name
//        }
//
//        void setName(String name) {
//            this.name = name
//        }
//
//        String getClassRoom() {
//            return classRoom
//        }
//
//        void setClassRoom(String classRoom) {
//            this.classRoom = classRoom
//        }
//
//        String getDate() {
//            return date
//        }
//
//        void setDate(String date) {
//            this.date = date
//        }

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
        return st;
    }

    @RequestMapping(value = "/helloHibernate", method = RequestMethod.POST)
    @Transactional
    public def hibernateWithSpring(@RequestParam Integer id, HttpServletRequest request){

        def JSON = getJSON(request);
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

            def School sc = new School();
            sc.setSchoolName("air force school");
//            session.save(sc);
            System.out.println("Done!");
        }
        catch(Exception e){
            print("deepanshu exception "+ e.getMessage());
        }
        finally {


        }

//        return (com.deepanshu.entity.Student)student;
        return list;
    }


    @RequestMapping(value = "/createEntities", method = RequestMethod.POST)
    @Transactional
    public def createEntities(HttpServletRequest request){
        def JSON = getJSON(request);
        if (JSON?.school){
            School sc = objectMapper.readValue(JSON?.school?.toString(), School.class);
            def metaData = sc.metaData;
            metaData.put("rabriKulfi", "roohafza wali rabri");
            metaData.put("matkaKulfi", "kasooti matka kulfi");
            def schoolSaved = createSchool(sc);
            print("deepanshu print school save" +  schoolSaved);
        }else if(JSON.student){
            com.deepanshu.entity.Student st = objectMapper.readValue(JSON?.student?.toString(), com.deepanshu.entity.Student.class);
            Session session  = sessionFactory.getCurrentSession();
            School school = session.get( School.class, JSON.studentSchool);
            st.setSchoolOne(school);
            def studentSaved = createStudent(st);
            print("deepanshu print student save" +  studentSaved);

        }
        Session session  = sessionFactory.getCurrentSession();
        School sc = session.get(School.class, JSON.studentSchool);
        print("deepanshu  "  + sc?.id);
        this.createSchool(sc);
    }

    @Transactional
    public def createSchool(School school){
        Session session = sessionFactory.getCurrentSession();
        return session.save(school);
    }

    @Transactional
    public def createStudent(com.deepanshu.entity.Student st){
        Session session = sessionFactory.getCurrentSession();
        return session.save(st);
    }
}




// todo : turn meta-data into blobs ,use lazy loading for their fetch, create a separate table for meta
// todo : use meta data from parent class inheritance so that boilerPlate code is not used again
// todo : make a generator for uuid generation, don't use self made methods
// todo : use json data type at databse layer,
// todo : look for a dataype in jpa that supports json in sql
// todo : lazy loading in all the many-one relations