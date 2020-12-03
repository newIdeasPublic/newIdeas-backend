package com.xslgy.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 字符串转jsonNode
     * @param str 需要转换的字符串
     * @return
     */
    public static JsonNode string2Json(String str) throws JsonProcessingException {
        return MAPPER.readTree(str);
    }

    /**
     * 对象转字符串
     * @param obj   需要转换的对象
     * @return
     * @throws JsonProcessingException
     */
    public static String object2String(Object obj) throws JsonProcessingException {
        return MAPPER.writeValueAsString(obj);
    }

    /**
     * 对象转json对象
     * @param obj   需要转换的对象
     * @return
     * @throws JsonProcessingException
     */
    public static JsonNode object2Json(Object obj) throws JsonProcessingException {
        return string2Json(object2String(obj));
    }

    /**
     * json转对象
     * @param json 需要转换的json
     * @param clazz 对象
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T json2Object(String json, Class clazz) throws JsonProcessingException {
        return (T) MAPPER.readValue(json, clazz);
    }

    /**
     * json转list
     * @param str 需要转换的字符串
     * @param clazz 需要转换的对象
     * @return
     * @throws JsonProcessingException
     */
    public static List<?> json2List(String str, Class clazz) throws JsonProcessingException {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        return MAPPER.readValue(str, javaType);
    }
}
