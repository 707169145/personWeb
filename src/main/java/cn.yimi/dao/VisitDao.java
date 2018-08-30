package cn.yimi.dao;

import cn.yimi.dto.VisitInfo;

/**
 * 拜访者信息
 * @author huangzs
 */
public interface VisitDao {

    /**
     * 添加拜访者信息
     * @param visitInfo
     * @return
     */
    public Integer insertVisitRecord(VisitInfo visitInfo);
}
