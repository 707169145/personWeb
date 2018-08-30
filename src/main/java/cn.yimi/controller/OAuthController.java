package cn.yimi.controller;

import cn.yimi.controller.result.ResultBuilder;
import cn.yimi.controller.result.ResultModal;
import cn.yimi.dto.UserInfo;
import cn.yimi.service.OAuthService;
import cn.yimi.util.IpUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
/**
 * 网站访问权限控制
 * 用户登录-验证-记录
 */
@Controller
@RequestMapping(value = "/OAuth")
public class OAuthController {
    private static Logger logger = Logger.getLogger(OAuthController.class);

    @Autowired
    OAuthService oAuthService;

    /**
     * 登录验证
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultModal login(UserInfo userInfo) {
        try{
            return ResultBuilder.success(oAuthService.login(userInfo));
        } catch (Exception e) {
            logger.error("登录验证" + e);
            return ResultBuilder.fail(-1, e.getMessage());
        }
    }

    /**
     * 请求被拦截-出错信息
     * @return
     */
    @RequestMapping(value = "/tokenError")
    @ResponseBody
    public ResultModal tokenError() {
        return ResultBuilder.fail(401);
    }

    /**
     * 访问记录
     */
    @RequestMapping(value = "/visitRecord")
    @ResponseBody
    public void VisitRecord() {
        oAuthService.recordVisit();
    }
}
