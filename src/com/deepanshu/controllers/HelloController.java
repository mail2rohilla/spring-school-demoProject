package com.deepanshu.controllers;

import com.deepanshu.pojos.Demo;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPBinding;
import java.io.BufferedReader;

@RestController
@RequestMapping("/controllerMap")
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Demo demoFunc(HttpServletRequest request){

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }
        System.out.println("deepu " + jb.toString());
        try{
            JSONObject obj = new JSONObject(jb.toString());
        }catch(JSONException ex){
            System.out.println("exception occured while parssing");
        }

        return new Demo(1,2,3,5);
    }
}
