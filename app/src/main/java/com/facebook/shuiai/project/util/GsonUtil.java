package com.facebook.shuiai.project.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * @author zengzebin shizhu@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2016/5/24
 */
public class GsonUtil {
    private static GsonUtil ourInstance = new GsonUtil();
    private Gson gson;

    private GsonUtil() {
        gson = new Gson();
    }

    public static GsonUtil getInstance() {
        if (ourInstance == null) {
            ourInstance = new GsonUtil();
        }
        return ourInstance;
    }

    public String toJson(Object object) {
        return gson.toJson(object);
    }

    public <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public <T> T fromJson(String json, T t) {
        return gson.fromJson(json, new TypeToken<T>() {
        }.getType());
    }

    public <T> T fromJson(String json, Type type)  {
        return gson.fromJson(json, type);
    }
}
