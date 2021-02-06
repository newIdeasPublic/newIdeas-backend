package com.xslgy.common.entity;

import com.xslgy.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author : 玄机
 * @Data : 2021/1/16  下午8:12
 * @Description : 社会组织实体
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "society_org")
@EqualsAndHashCode(callSuper = true)
public class SocietyOrg extends BaseEntity {

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 联系电话
     */
    @Column(name = "mobile", length = 20)
    private String mobile;

    /**
     * 组织地址
     */
    private String orgAddress;

    /**
     * 组织邮箱
     */
    private String orgMail;

    /**
     * 资质证书
     */
    private String orgCertificationUrl;

    /**
     * 组织Logo
     */
    private String orgLogoUrl;

    /**
     * 组织介绍
     */
    @Column(name = "org_introduction", length = 1000)
    private String orgIntroduction;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 所在社区
     */
    private String community;

    /**
     * 所在分类
     */
    private String category;

    /**
     * 法人姓名
     */
    private String juridicalPerson;

    /**
     * 法人介绍
     */
    @Column(name = "juridical_person_introduction", length = 1000)
    private String juridicalPersonIntroduction;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 联系人电话
     */
    private String linkmanMobile;

    /**
     * 联系人介绍
     */
    @Column(name = "linkman_introduction", length = 1000)
    private String linkmanIntroduction;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除 0-否 1-是
     */
    private Byte deleteFlag;
    /**
     * 状态
     */
    private Integer status;
}
