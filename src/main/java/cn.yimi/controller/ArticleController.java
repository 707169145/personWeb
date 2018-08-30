package cn.yimi.controller;

import cn.yimi.controller.result.ResultBuilder;
import cn.yimi.controller.result.ResultModal;
import cn.yimi.dto.ArticleDto;
import cn.yimi.service.ArticleService;
import cn.yimi.vo.ArticleVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/article")
@Controller
public class ArticleController {
    private static Logger logger = Logger.getLogger(ArticleController.class);

    @Autowired
    ArticleService articleService;

    /**
     * 获取文章列表
     * @param articleVo
     * @return
     */
    @RequestMapping(value = "/getArticleList")
    @ResponseBody
    public ResultModal getArticleList(ArticleVo articleVo) {
        try {
            return ResultBuilder.success(articleService.getArticleList(articleVo));
        } catch (Exception e) {
            logger.error("获取文章列表" + e);
            return ResultBuilder.fail(e.toString());
        }
    }

    /**
     * 获取数据总条数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getArticleCount")
    public ResultModal getArticleCount(String condition) {
        try {
            return ResultBuilder.success(articleService.getArticleCount(condition));
        }  catch (Exception e) {
            logger.error("获取数组总条数" + e);
            return ResultBuilder.fail(e.getMessage());
        }
    }

    /**
     * 删除文章
     * @param articleId
     *      文章id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/system/delArticle")
    public ResultModal delArticle(String articleId) {
        try {
            return ResultBuilder.success(articleService.delArticle(articleId));
        } catch (Exception e) {
            logger.error("删除文章" + e);
            return ResultBuilder.fail(e.getMessage());
        }
    }

    /**
     * 置顶文章
     * @param articleId
     *      文章id
     * @param up
     *      置顶、取消
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/system/upArticle")
    public  ResultModal upArticle(String articleId, String up) {
        try {
            return ResultBuilder.success(articleService.upArticle(articleId, up));
        } catch (Exception e) {
            logger.error("置顶文章" + e);
            return ResultBuilder.fail(e.getMessage());
        }
    }

    /**
     * 新增文章
     * @param articleDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/system/insertArticle")
    public ResultModal insertArticle(ArticleDto articleDto) {
        try {
            return ResultBuilder.success(articleService.insertArticle(articleDto));
        } catch (Exception e) {
            logger.error("新增文章" + e);
            return ResultBuilder.fail(e.getMessage());
        }
    }
}
