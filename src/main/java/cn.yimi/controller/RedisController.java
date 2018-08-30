package cn.yimi.controller;

import cn.yimi.controller.result.ResultBuilder;
import cn.yimi.controller.result.ResultModal;
import cn.yimi.service.RedisService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/redis")
public class RedisController {

    private static Logger logger = Logger.getLogger(RedisController.class);

    @Autowired
    RedisService redisService;

    @RequestMapping(value = "/setCache")
    @ResponseBody
    public ResultModal serCache(String key, String value) {
        redisService.setCache(key, value);
        return ResultBuilder.success();
    }

    @RequestMapping(value = "/clearCache")
    @ResponseBody
    public ResultModal clearCache() {
        try{
            return ResultBuilder.success(redisService.clearCache());
        } catch(Exception e) {
            logger.error("清除系统缓存" + e);
            return ResultBuilder.fail(e.toString());
        }
    }
}
