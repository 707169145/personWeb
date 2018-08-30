package cn.yimi.dto;

/**
 * 菜单实体类
 * @author huangzs
 */
public class MenuDto {
    // 菜单名字
    private String menuTitle;
    // 菜单链接
    private String menuUrl;
    // 菜单等级（是否有子菜单）
    private String menuLeave;
    // 菜单权限类别，谁可以访问
    private String menuType;
    // 菜单图标
    private String menuIcon;

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuLeave() {
        return menuLeave;
    }

    public void setMenuLeave(String menuLeave) {
        this.menuLeave = menuLeave;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }
}
