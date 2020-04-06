package com.deepanshu.controllers;

import com.deepanshu.pojos.Demo;
import com.deepanshu.pojos.Demo1;
import com.deepanshu.pojos.Demo2;
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
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/controllerMap")
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public Demo demoFunc(HttpServletRequest request, @RequestBody Demo demo){

//        System.out.println(demo);
//        StringBuffer jb = new StringBuffer();
//        String line = null;
//        try {
//            InputStream in = request.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            while ((line = reader.readLine()) != null)
//                jb.append(line);
//        } catch (Exception e) { /*report an error*/
//            System.out.println(e);}
//        System.out.println("deepu " + jb.toString());
//        try{
//            JSONObject obj = new JSONObject(jb.toString());
//        }catch(JSONException ex){
//            System.out.println("exception occured while parssing");
//        }

        return demo;
    }
}
