package cn.yimi.dao;

import cn.yimi.dto.MessageDto;
import cn.yimi.vo.MessageVo;

import java.util.List;

/**
 * 消息相关操作
 */
public interface MessageDao {

    /**
     * 新增一条消息
     * @param messageDto
     *      消息实体
     * @return
     */
    public Integer insertMessage(MessageDto messageDto);

    /**
     * 删除一条消息
     * @param messageId
     *      消息id
     * @return
     */
    public Integer delMessage(String messageId);

    /**
     * 获取留言列表
     * @param messageVo
     * @return
     */
    public List<MessageDto> getMessageList(MessageVo messageVo);

    /**
     * 获取总记录条数
     * @return
     */
    public Integer getCount(String author);
}
