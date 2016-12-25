package com.starry.sky.jacksondemo;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by Administrator on 2016/12/25.
 * 只用创建一次ObjectMapper对象就可以了
 */

public class JacksonMapper {
    private static ObjectMapper mapper ;
    public static ObjectMapper getMapper(){
        if(mapper==null){
            synchronized (JacksonMapper.class){
                if(mapper == null){
                    mapper = new ObjectMapper();
                }
            }
        }
        return mapper;
    }
}
