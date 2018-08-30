package cn.yimi.service;

import cn.yimi.dao.MenuDao;
import cn.yimi.dto.MenuDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 菜单业务处理
 */
@Service
public class MenuService {
    private static Logger logger = Logger.getLogger(MenuService.class);

    @Autowired
    MenuDao menuDao;

    @Autowired
    OAuthService oAuthService;

    /**
     * 根据用户类型获取菜单
     * @param userType
     * @return
     */
    public List<MenuDto> getMenu(String userType) {
        logger.info("菜单加载，当前用户类型：" + userType);
        // 每次访问加载菜单时记录访问人信息
        oAuthService.recordVisit();

        if (StringUtils.isEmpty(userType)) {
            // 默认给予普通用户权限
            userType = "1";
        }
        List<MenuDto> menuList = menuDao.getAllMenu();
        if (null == menuList || 0 == menuList.size()) {
            logger.error("菜单栏目为空");
            throw new RuntimeException("菜单栏目为空");
        }

        for (int i = menuList.size()-1; i >= 0 ; i--) {
            MenuDto menu = menuList.get(i);
            if (-1 == userType.indexOf(menu.getMenuType())){
                // 无权限访问此菜单
                menuList.remove(menu);
            }
        }

        return menuList;
    }
}
