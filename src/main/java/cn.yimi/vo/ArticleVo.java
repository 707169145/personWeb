package cn.yimi.vo;

import cn.yimi.dto.ArticleDto;

import java.io.Serializable;

/**
 * 文章信息拓展类
 * 用于列表展示
 * @author huangzs
 */
public class ArticleVo extends ArticleDto implements Serializable {
    // 页码
    private Integer page;
    // 总记录数
    private String count;
    // 一页有多少条
    private Integer pageSize;
    // 查询条件1
    private String condition;
    // 是否使用缓存
    private String cache;

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
