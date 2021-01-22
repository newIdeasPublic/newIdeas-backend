package com.xslgy.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xslgy.core.entity.BaseEntity;

import lombok.Data;

/**
 * @author lamdaer
 * @createTime 2020/12/15
 */
@Data
@Entity
@Table(name = "partner")
public class Partner extends BaseEntity {
    private String type;
    private String companyName;
    private String name;
    private String position;
    private String phoneNumber;
    private String address;
    private String email;
    private String remark;
}
