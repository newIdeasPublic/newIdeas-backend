package com.xslgy.common.entity;

import com.xslgy.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "msg_message_config")
public class MessageConfig extends BaseEntity {

    /**
     * 消息唯一标识
     *
     */
    private String code;
    /**
     * 消息名称
     */
    private String name;
    /**
     * 消息模板id
     */
    private String templateId;
    /**
     * 消息诶荣
     */
    private String content;
    /**
     * 消息参数
     */
    private String params;
}
