package com.quiz.controllers

import org.json.JSONObject

import javax.servlet.http.HttpServletRequest

class JSONController {

    JSONObject getJSON(HttpServletRequest httpRequest){
        return httpRequest?.request?.notes?.JSON;
    }
}
