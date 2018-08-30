package cn.yimi.service;

import cn.yimi.dao.MessageDao;
import cn.yimi.dto.MessageDto;
import cn.yimi.util.SessionUtil;
import cn.yimi.vo.MessageVo;
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
 * 留言相关操作
 */
@Service
public class MessageService {
    private static Logger logger = Logger.getLogger(MessageService.class);

    @Autowired
    MessageDao messageDao;

    @Autowired
    RedisService redisService;

    /**
     * 取得留言列表
     * @param messageVo
     *      消息实体拓展类
     * @return
     */
    public List<MessageDto> getMessageLit(MessageVo messageVo) {
        logger.info("获取留言列表,用户：" + SessionUtil.getSession("empid"));

        messageVo.setPageSize(10);
        // 计算分页条件
        if (StringUtils.isEmpty(messageVo.getPage())) {
            messageVo.setPage(0);
        } else {
            messageVo.setPage((messageVo.getPage()-1) * messageVo.getPageSize());
        }

        messageVo.setAuthor(SessionUtil.getSession("empid").toString());

        String cacheKey = List.class.getSimpleName() + MessageVo.class.getSimpleName() + messageVo.getAuthor() + messageVo.getPage();
        List<MessageDto> list = new ArrayList<MessageDto>();
        // 使用缓存
        if ("cache".equals(messageVo.getCache())) {
            try {
                list = (List<MessageDto>) redisService.getCache(cacheKey, list.getClass());
            } catch (Exception e) {
                logger.info("从缓存中读取不到数据！" + e.toString());
            }
            if (null != list && list.size() > 0) {
                logger.info("从缓存中读取数据！");
                return list;
            }
        }
        list = messageDao.getMessageList(messageVo);
        redisService.setCache(cacheKey, list);

        return list;
    }

    /**
     * 获取记录条数
     * @return
     */
    public Integer getCount() {
        return messageDao.getCount(SessionUtil.getSession("empid").toString());
    }

    /**
     * 删除留言
     * @param messageId
     *      留言id
     * @return
     */
    public Integer delMessage(String messageId) {
        logger.info("删除留言" + messageId);

        redisService.delCache(List.class.getSimpleName() + MessageVo.class.getSimpleName() + SessionUtil.getSession("empid").toString());
        return messageDao.delMessage(messageId);
    }

    /**
     * 新增留言
     * @param messageDto
     *      留言实体
     * @return
     */
    public Integer insertMessage(MessageDto messageDto) {
        logger.info("新增留言" + messageDto);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        messageDto.setRecordTime(format.format(new Date()));

        redisService.delCache(List.class.getSimpleName() + MessageVo.class.getSimpleName() + SessionUtil.getSession("empid").toString());
        return messageDao.insertMessage(messageDto);
    }
}
