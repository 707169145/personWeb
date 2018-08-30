package cn.yimi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;

import java.io.*;

/**
 * 对象序列化与反序列化工具
 * 目前使用json字符串的序列化方式，不采用字节流的形式
 * @author huangzs
 */
public class SerializaUtil {

    /**
     * 借助jackjson将对象转为json字符串
     * @param object
     * @return
     */
    public static String objectToJsonString(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 借助jackjson将josn字符串反序列化为对象
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>T jsonStringToObject(String jsonString, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try {
            t = mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将对象序列化
     * @param object
     *      需要序列化的对象
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;

        try {
            //序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 反序列化出对应的对象
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        if (null == bytes || bytes.length <= 0) {
            return null;
        }

        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
