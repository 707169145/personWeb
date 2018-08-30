package cn.yimi.dao;

import cn.yimi.dto.MenuDto;

import java.util.List;

/**
 * 系统菜单
 */
public interface MenuDao {
    /**
     * 获取全部菜单信息
     * @return
     */
    public List<MenuDto> getAllMenu();
}
