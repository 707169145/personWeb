package cn.yimi.service;

import cn.yimi.dao.ArticleDao;
import cn.yimi.dto.ArticleDto;
import cn.yimi.vo.ArticleVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文章相关操作
 * @author huangzs
 */
@Service
public class ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    RedisService redisService;

    private static Logger logger = Logger.getLogger(ArticleService.class);

    /**
     * 获取文章列表
     * @return
     */
    public List<ArticleVo> getArticleList(ArticleVo articleVo) {
        logger.info("获取文章列表（后台分页）" + articleVo);

        articleVo.setPageSize(10);
        // 计算分页条件
        if (StringUtils.isEmpty(articleVo.getPage())) {
            articleVo.setPage(0);
        } else {
            articleVo.setPage((articleVo.getPage()-1) * articleVo.getPageSize());
        }

        String cacheKey = List.class.getSimpleName() + ArticleVo.class.getSimpleName() + articleVo.getPage();
        List<ArticleVo> list = new ArrayList<ArticleVo>();

        // 使用缓存
        if ("cache".equals(articleVo.getCache())) {
            try {
                list = (List<ArticleVo>) redisService.getCache(cacheKey, list.getClass());
            } catch(Exception e) {
                logger.debug("缓存中不存在该对象");
            }
            if (null != list && list.size() > 0) {
                logger.info("从缓存读取对象使用");
                return list;
            }
        }

        logger.info("从数据库中读取数据");
        list = articleDao.getArticleList(articleVo);
        if (articleVo.getCache().equals("cache")) {
            logger.info("设置缓存" + list);
            redisService.setCache(cacheKey, list);
        }

        return list;
    }

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    public Integer delArticle(String articleId) {
        logger.info("删除文章" + articleId);

        redisService.delCache(List.class.getSimpleName() + ArticleVo.class.getSimpleName());
        return articleDao.delArticle(articleId);
    }

    /**
     * 文章总条数
     * @return
     */
    public Integer getArticleCount(String condition) {
        logger.info("获取文章总条数,筛选条件=" + condition);
        return articleDao.getCountArticle(condition);
    }

    /**
     * 置顶文章
     * @param articleId
     *      文章id
     * @return Integer
     */
    public Integer upArticle(String articleId, String up) {
        logger.info("文章置顶操作" + articleId + "设置状态（1置顶，0取消）" + up);
        redisService.delCache(List.class.getSimpleName() + ArticleVo.class.getSimpleName());
        return articleDao.upArticle(articleId,up);
    }

    /**
     * 新增文章
     * @param articleDto
     * @return
     */
    public Integer insertArticle(ArticleDto articleDto) {
        logger.info("新增文章" + articleDto);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        articleDto.setRecordTime(format.format(new Date()));

        redisService.delCache(List.class.getSimpleName() + ArticleVo.class.getSimpleName());

        return articleDao.insertArticle(articleDto);
    }
}
