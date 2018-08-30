package cn.yimi.service;

import cn.yimi.dao.UserDao;
import cn.yimi.dao.VisitDao;
import cn.yimi.dto.UserInfo;
import cn.yimi.dto.VisitInfo;
import cn.yimi.util.MD5Util;
import cn.yimi.util.SessionUtil;
import cn.yimi.util.TokenUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录 - 验证 - 权限
 */
@Service
public class OAuthService {
    private static Logger logger = Logger.getLogger(OAuthService.class);

    @Autowired
    UserDao userDao;

    @Autowired
    VisitDao visitDao;

    /**
     * 获取用户信息
     * @param userInfo
     * @return
     */
    public Map<String, Object> login(UserInfo userInfo) throws UnsupportedEncodingException {
        logger.info("用户登录，" + userInfo);

        UserInfo user = userDao.getUserInfo(userInfo);
        Map<String, Object> map = new HashMap<>();
        String password = MD5Util.stringTomd5(userInfo.getPassword());

        if (null != user && !StringUtils.isEmpty(user.getEmpid()) && user.getPassword().equals(password)) {
            String token = TokenUtil.createToken(user.getEmpid());
            user.setPassword("");
            map.put("token", token);
            map.put("user", user);
            return map;
        }

        logger.error("找不到用户信息" + userInfo);
        return null;
    }

    /**
     * 添加访问记录，地址暂时未添加
     */
    public void recordVisit() {
        logger.info("用户访问记录：" + SessionUtil.getSession("empid"));

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        VisitInfo visitInfo = new VisitInfo();
        visitInfo.setVisit_ip(SessionUtil.getSession("ip").toString());
        visitInfo.setVisit_time(format.format(new Date()));
        visitInfo.setVisiter(SessionUtil.getSession("empid").toString());
        visitDao.insertVisitRecord(visitInfo);
    }

    /**
     * 判断是否为系统管理员
     * @return
     */
    public Boolean isSystemRole(String empid) {
        logger.info("用户类型判断" + empid);

        UserInfo userInfo = new UserInfo();
        userInfo.setEmpid(empid);
        UserInfo user = userDao.getUserInfo(userInfo);

        if (null != user && user.getRole().equals("0")) {
            return true;
        }

        return false;
    }
}
