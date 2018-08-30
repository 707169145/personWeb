package cn.yimi.util;

import java.util.HashMap;
import java.util.Map;

/**
 * session工具类，获取用户信息
 * @author huangzs
 */
public class SessionUtil {

    private static Map<String, Object> map = new HashMap<String, Object>();

    /**
     * 设置session
     * @param key
     * @param value
     */
    public static void setSession(String key, String value) {
        map.put(key, value);
    }

    /**
     * 获取session
     * @param key
     * @return
     */
    public static Object getSession(String key) {
        return map.get(key);
    }
}
