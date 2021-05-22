package com.quiz.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.logging.log4j.LogManager
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired

import javax.servlet.http.HttpServletRequest
import java.util.logging.Logger

class JSONController {

    JSONController() {

    }

    JSONController(Class<?> classForLogging){
        logger = LogManager.getLogger(classForLogging);
    }


    @Autowired
    ObjectMapper objectMapper;
    org.apache.logging.log4j.Logger logger;

    JSONObject getJSON(HttpServletRequest httpRequest){
        return httpRequest?.request?.notes?.JSON;
    }


    private void debug(String string){
        if (logger.isDebugEnabled()) {
            logger.debug("", num);
        }
    }

}
