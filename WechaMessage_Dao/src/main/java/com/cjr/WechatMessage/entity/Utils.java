package com.cjr.WechatMessage.entity;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import java.io.IOException;
import java.util.List;

//转换json工具类
public class Utils {


    public static<T> String objectToJson(T obj){

        try {
            String back = JSONSerializer.toJSON(obj).toString();
            return back;
        }catch (JSONException e){
            System.out.println("obj to string error");
            e.printStackTrace();
            return null;
        }

    }

    public static JSONObject stringToJSON(String jsonstr){
        try {
            JSONObject jsonObject = JSONObject.fromObject(jsonstr);
            return jsonObject;
        }catch (JSONException e){
            System.out.println("string to json error");
            e.printStackTrace();
            return null;
        }

    }

}

