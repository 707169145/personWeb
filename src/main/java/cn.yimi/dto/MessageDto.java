package cn.yimi.dto;

import java.io.Serializable;

/**
 * 留言实体类
 * @author  huangzs
 */
public class MessageDto implements Serializable {
    // 消息体
    private String message;
    // 发布时间
    private String recordTime;
    // 作者（游客用ip代替）
    private String author;
    // 类型（公开或私有）
    private String type;
    // 数据状态
    private String status;
    // 消息id
    private String messageId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
