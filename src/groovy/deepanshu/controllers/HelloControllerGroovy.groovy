package deepanshu.controllers

import com.deepanshu.pojos.Demo1
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper
import deepanshu.pojos.Demo;
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
@RequestMapping("/controllerMapGroovy")
public class HelloControllerGroovy {

    @RequestMapping(value = "/helloGroovy", method = RequestMethod.POST)
    public def demoFunc(HttpServletRequest request) {
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

        def temp = new com.deepanshu.pojos.Demo(1,2,3,4, new Demo1(3,4,5))
        return dem;
    }
}
