package cn.yimi.controller;

import cn.yimi.controller.result.ResultBuilder;
import cn.yimi.controller.result.ResultModal;
import cn.yimi.dto.MessageDto;
import cn.yimi.service.MessageService;
import cn.yimi.util.IpUtil;
import cn.yimi.vo.MessageVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 留言API
 * @author huangzs
 */
@RequestMapping(value = "/message")
@Controller
public class MessageController {
    private static Logger logger = Logger.getLogger(MessageController.class);

    @Autowired
    MessageService messageService;

    /**
     * 获取留言列表
     * 取得公开的以及自己的
     * @param messageVo
     * @return
     */
    @RequestMapping(value = "/getMessageList")
    @ResponseBody
    public ResultModal getMessageList(MessageVo messageVo) {
        try {
            return ResultBuilder.success(messageService.getMessageLit(messageVo));
        } catch(Exception e) {
            logger.error("获取留言列表" + e);
            return ResultBuilder.fail(e.toString());
        }
    }

    /**
     * 获取留言总记录数
     * @return
     */
    @RequestMapping(value = "/getCount")
    @ResponseBody
    public ResultModal getCount() {
        try {
            return ResultBuilder.success(messageService.getCount());
        } catch(Exception e) {
            logger.error("获取留言总记录数" + e);
            return ResultBuilder.fail(e.toString());
        }
    }

    /**
     * 删除留言
     * @param messageId
     *      留言id
     * @return
     */
    @RequestMapping(value = "/system/delMessage")
    @ResponseBody
    public ResultModal delMessage(String messageId) {
        try {
            return ResultBuilder.success(messageService.delMessage(messageId));
        } catch(Exception e) {
            logger.error("删除留言" + e);
            return ResultBuilder.fail(e.toString());
        }
    }

    /**
     * 新增留言信息
     * @return
     */
    @RequestMapping(value = "/insertMessage")
    @ResponseBody
    public ResultModal insertMessage(MessageDto messageDto, HttpServletRequest request) {
        try {
            if (StringUtils.isEmpty(messageDto.getAuthor())) {
                messageDto.setAuthor(IpUtil.getIpAddress(request));
            }

            return ResultBuilder.success(messageService.insertMessage(messageDto));
        } catch(Exception e) {
            logger.error("新增留言信息" + e);
            return ResultBuilder.fail(e.toString());
        }
    }
}
