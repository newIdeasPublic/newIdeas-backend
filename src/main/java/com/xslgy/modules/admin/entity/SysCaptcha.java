package com.xslgy.modules.admin.entity;

import com.xslgy.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "sys_captcha")
public class SysCaptcha extends BaseEntity {

    private String uuid;
    private String code;
    private Date expireTime;
}
