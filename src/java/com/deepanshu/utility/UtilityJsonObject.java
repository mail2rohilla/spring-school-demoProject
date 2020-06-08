package com.deepanshu.utility;

import org.json.JSONException;
import org.json.JSONObject;

public class UtilityJsonObject extends JSONObject {

    public UtilityJsonObject(String string){
        super(string);
    }
    @Override
    public Object get(String key) throws JSONException {
        if (key == null) {
            throw new JSONException("Null key.");
        } else {
            Object object = this.opt(key);
            if (object == null) {
                return null;
            } else {
                return object;
            }
        }
    }

}
