package cn.yimi.vo;

import cn.yimi.dto.MessageDto;

import java.io.Serializable;

/**
 * 留言拓展实体类-分页
 */
public class MessageVo extends MessageDto implements Serializable {
    // 页码
    private Integer page;
    // 总记录数
    private String count;
    // 一页有多少条
    private Integer pageSize;
    //  缓存
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
}
