package com.quiz.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired

import javax.servlet.http.HttpServletRequest

class JSONController {

    @Autowired
    ObjectMapper objectMapper;

    JSONObject getJSON(HttpServletRequest httpRequest){
        return httpRequest?.request?.notes?.JSON;
    }
}
