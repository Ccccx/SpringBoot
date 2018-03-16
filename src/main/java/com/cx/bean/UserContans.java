package com.cx.bean;

import sun.security.jca.GetInstance;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author CX
 * @create 2018/3/14
 */
public class UserContans {
    private  ConcurrentHashMap<String,Object> maps;
    private static UserContans userContans;
    private UserContans() {
        maps = new ConcurrentHashMap<>(16);
    }

    public static UserContans getInstance(){
        if(userContans != null) {
           return userContans;
        }
        synchronized (Object.class) {
            if(userContans != null) {
                return userContans;
            }
            return userContans = new UserContans();
        }

    }

    public Object getKey(String key) {
        return this.maps.get(key);
    }

    public void setMaps(String key,Object value) {
        this.maps.put(key,value);
    }
}
