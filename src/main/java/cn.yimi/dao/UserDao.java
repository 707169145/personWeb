package cn.yimi.dao;

import cn.yimi.dto.UserInfo;

import java.util.List;

/**
 * 用户信息
 * @author huangzs
 */
public interface UserDao {
    /**
     * 根据用户empid获取用户信息
     * @param userInfo
     * @return
     */
    public UserInfo getUserInfo (UserInfo userInfo);

    /**
     * 获取全部用户信息
     * @return
     */
    public List<UserInfo> getAllUserInfo ();

    /**
     * 新增一条基本用户信息
     * @param userInfo
     * @return
     */
    public int insertUserInfo (UserInfo userInfo);
}
