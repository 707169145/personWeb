package cn.yimi.controller;

import cn.yimi.controller.result.ResultBuilder;
import cn.yimi.controller.result.ResultModal;
import cn.yimi.service.MenuService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 菜单业务处理
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {
    private static Logger logger = Logger.getLogger(MenuController.class);

    @Autowired
    MenuService menuService;

    /**
     * 根据用户等级获取不同的菜单
     * @return
     */
    @RequestMapping(value = "/getMenu")
    @ResponseBody
    public ResultModal getMenuByUserType(String userType) {
        try {
            return ResultBuilder.success(menuService.getMenu(userType));
        } catch (Exception e) {
            logger.error("获取菜单信息" + e);
            return ResultBuilder.fail(e.toString());
        }
    }
}
