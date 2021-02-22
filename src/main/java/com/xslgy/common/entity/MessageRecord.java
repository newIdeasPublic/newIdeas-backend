package com.xslgy.common.entity;

import com.xslgy.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "msg_message_record")
public class MessageRecord extends BaseEntity {

    private Integer type;
    private String receiver;
    private String content;
    private Integer successFlag;
}
