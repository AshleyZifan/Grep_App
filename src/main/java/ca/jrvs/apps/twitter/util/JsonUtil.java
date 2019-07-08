package ca.jrvs.apps.twitter.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;


public class JsonUtil {

    public static String toPrettyJson(Object object) throws JsonProcessingException {
        return toJson(object, true, false);
    }

    /**
     * Convert a java object to JSON string
     * @param object input object
     * @return JSON String
     * @throws JsonProcessingException
     */
    public static String toJson(Object object, boolean prettyJson, boolean includeNullValues) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if (prettyJson){
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
        }
        if (includeNullValues){
            mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        }
        return mapper.writeValueAsString(object);
    }

    /**
     * Parse JSON string to a object
     * @param json JSON str
     * @param clazz object class
     * @param <T> Type
     * @return Object
     * @throws IOException
     */
    public static <T> T toObjectFromJson(String json, Class clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T)objectMapper.readValue(json, clazz);
    }
}
