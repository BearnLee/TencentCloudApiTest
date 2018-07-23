package com.unicom.tencent.utils;

import java.util.Map;
import java.util.TreeMap;

/*
 * 用户辅助腾讯请求API的类
 */
public class TreeMapValueBuilder {
    private TreeMap<String, Object> mValues = new TreeMap<String, Object>();

    public TreeMapValueBuilder put(String key,String value){
        mValues.put(key, value);
        return this;
    }

    public TreeMapValueBuilder put(String key,int value){
        mValues.put(key, String.valueOf(value));
        return this;
    }

    public TreeMapValueBuilder put(String key,long value){
        mValues.put(key, String.valueOf(value));
        return this;
    }

    public TreeMapValueBuilder put(String key,boolean value){
        mValues.put(key, value ? "1" : "0");
        return this;
    }

    public TreeMapValueBuilder put(String key,float value){
        mValues.put(key, String.valueOf(value));
        return this;
    }

    public TreeMapValueBuilder put(String key,double value){
        mValues.put(key, String.valueOf(value));
        return this;
    }

    public TreeMapValueBuilder putAll(Map<String,Object> values){
        mValues.putAll(values);
        return this;
    }

    public TreeMap<String, Object> build(){
        return mValues;
    }
}
