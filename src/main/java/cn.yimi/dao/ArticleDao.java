package cn.yimi.dao;

import cn.yimi.dto.ArticleDto;
import cn.yimi.vo.ArticleVo;

import java.util.List;

/**
 * 文章操作类
 * @author huangzs
 */
public interface ArticleDao {
    /**
     * 新增文章
     * @param articleDto
     *      文章信息
     * @return Integer
     */
    public Integer insertArticle(ArticleDto articleDto);

    /**
     * 删除文章
     * @param articleId
     *      文章id
     * @return Integer
     */
    public Integer delArticle(String articleId);

    /**
     * 获取文章列表
     * @return
     */
    public List<ArticleVo> getArticleList(ArticleVo articleVo);

    /**
     * 获取数据总条数
     * @return
     */
    public Integer getCountArticle(String condition);

    /**
     * 置顶文章
     * @return
     */
    public Integer upArticle(String articleId, String up);
}
