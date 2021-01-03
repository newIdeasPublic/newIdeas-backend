package com.xslgy.common.entity;

import com.xslgy.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "mms_member")
public class Member extends BaseEntity {

    /**
     * 用户名
     */
    private String username;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 范围：个人、个体工商、企业
     */
    private String scope;
    /**
     * 盐
     */
    private String salt;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态：0启用，1禁用
     */
    private Integer status;
}
