package cn.yimi.dto;

import java.io.Serializable;

/**
 * 文章基本信息类
 * @author huangzs
 */
public class ArticleDto implements Serializable {
    // 文章标题
    private String articleName;
    // 文章链接
    private String articleUrl;
    // 文章排序
    private String articleUp;
    // 数据状态
    private String status;
    // 文章id
    private String articleId;
    // 录入时间
    private String recordTime;

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticleUp() {
        return articleUp;
    }

    public void setArticleUp(String articleUp) {
        this.articleUp = articleUp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
