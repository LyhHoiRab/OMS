package com.oms.commons.config.converter;

import com.google.gson.Gson;
import com.wah.doraemon.utils.GsonUtils;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

public class JsonHttpMessageConverter extends GsonHttpMessageConverter{

    private Gson gson;

    public JsonHttpMessageConverter(){
        super.setGson(getGson());
    }

    public Gson getGson(){
        if(gson == null){
            gson = GsonUtils.getGson();
        }

        return gson;
    }
}
