package cn.yimi.service;

import cn.yimi.util.SerializaUtil;
import cn.yimi.util.SessionUtil;
import cn.yimi.vo.ArticleVo;
import cn.yimi.vo.MessageVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

@Service
public class RedisService {
    private static Logger logger = Logger.getLogger(RedisService.class);

    @Autowired
    private JedisPool jedisPool;

    /**
     * 设置缓存
     */
    public void setCache(String key, Object value) {
        logger.info("设置缓存，key=" + key + "value=" + value);
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
        } catch (Exception e){
            logger.warn("加载redis失败" + e.toString());
            return;
        }

        jedis.set(key, SerializaUtil.objectToJsonString(value));
    }

    /**
     * 取值
     * @param key
     * @return
     */
    public <T>T getCache(String key, Class<T> clazz) {
        logger.info("redis取值,key=" + key);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception e){
            logger.warn("加载redis失败" + e.toString());
            return null;
        }

        return SerializaUtil.jsonStringToObject(jedis.get(key), clazz);
    }

    /**
     * 删除缓存
     * @param key
     * @return
     */
    public void delCache(String key) {
        logger.info("删除redis缓存，以" + key + "开头的");
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception e){
            logger.warn("加载redis失败" + e.toString());
            return;
        }
        Set<String> set = jedis.keys(key+"*");
        for (String keys : set) {
            jedis.del(keys);
        }
    }

    /**
     * 清除系统缓存
     * @return
     */
    public Boolean clearCache() {
        delCache(List.class.getSimpleName() + ArticleVo.class.getSimpleName());
        delCache(List.class.getSimpleName() + MessageVo.class.getSimpleName());
        return true;
    }
}
