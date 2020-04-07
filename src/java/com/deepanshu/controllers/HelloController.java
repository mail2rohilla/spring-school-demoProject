package com.deepanshu.controllers;

import com.deepanshu.pojos.Demo;
import com.deepanshu.pojos.Demo1;
import com.deepanshu.pojos.Demo2;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPBinding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/controllerMap")
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public Demo demoFunc(HttpServletRequest request){

//        System.out.println(demo);
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            InputStream in = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/
            System.out.println(e);}
        System.out.println("deepu " + jb.toString());
        try{
            JSONObject obj = new JSONObject(jb.toString());

            ObjectMapper om = new ObjectMapper();
            Demo1 dem = (Demo1)om.readValue(obj.get("demoVar").toString(), Demo1.class);

        }catch(JSONException ex){
            System.out.println("exception occured while parssing");
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Demo(1,2,3,4, new Demo1(3,4,5));
    }
}







//  TODO : make a generic JSON parser from request body
//  TODO : make a generic utility method for object mapping
//  TODO : make a filter for all the api's
//  TODO : make this project in groovy
//  TODO : make project auto build and deploy on change in the project